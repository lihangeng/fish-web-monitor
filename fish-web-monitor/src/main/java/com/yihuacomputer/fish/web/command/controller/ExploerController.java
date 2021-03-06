package com.yihuacomputer.fish.web.command.controller;


import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.file.FileMD5;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpFileRet;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.AgentRet;
import com.yihuacomputer.fish.web.command.format.DiskForm;
import com.yihuacomputer.fish.web.command.format.DownFileForm;
import com.yihuacomputer.fish.web.command.format.ExplorerForm;
import com.yihuacomputer.fish.web.command.format.ExplorerParamForm;
import com.yihuacomputer.fish.web.command.format.FileSystemForm;
import com.yihuacomputer.fish.web.command.format.MyComputerForm;

/**
 * 远程浏览控制.
 *
 * @author huxiaobao
 *
 */
@ClassNameDescrible(describle="userlog.ExploerController")
@Controller
@RequestMapping("/agent/remoteBrowse")
public class ExploerController
{
	private Logger logger = LoggerFactory.getLogger(ExploerController.class);
	
	private final static long MAXFILESIZE=209715200L;
	private final static long SIZEOFPERM=1024*1024L;
	
    /**
     * 通过URL请求获得Agent所采集的ATM的磁盘信息：
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
	@Autowired
	private MessageSource messageSource;
	
    /**
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchDisk(@RequestParam int start, @RequestParam int limit,
            WebRequest request)
    {
        ModelMap result = new ModelMap();
		String url = MonitorCfg.getHttpUrl(request.getParameter("ip"))+"/ctr/computer";
        MyComputerForm myComputerForm = (MyComputerForm) HttpProxy.httpGet(url,MyComputerForm.class, 5000);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", DiskForm.convert(myComputerForm.getMyComputerList()));
        return result;
    }

    /**
     * 通过URL请求获得Agent所采集的ATM的文件系统信息：
     * @param path
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/fileSystem")
    public @ResponseBody
    ModelMap searchFileSystem(@RequestParam String path,
            @RequestParam int start, @RequestParam int limit, WebRequest request)
    {
        ModelMap result = new ModelMap();
        ExplorerParamForm explorerParamForm = new ExplorerParamForm();
        explorerParamForm.setLimit(limit);
        explorerParamForm.setOffset(start);
        String pathValue = path;
        if(pathValue.endsWith(":")){
        	pathValue = pathValue + System.getProperty("file.separator");
        }
        explorerParamForm.setPath(pathValue);
		String url = MonitorCfg.getHttpUrl(request.getParameter("ip"))+"/ctr/explorer";
        ExplorerForm explorerForm = (ExplorerForm) HttpProxy.httpPost(url,explorerParamForm,ExplorerForm.class, 5000);
        if(explorerForm.getRet().equals(AgentRet.RET00)){
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("data", FileSystemForm.convert(explorerForm.getFileSystemList()));
        }else{
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }

    /**
     * 下载文件到服务端：
     * @param requestPath
     * @param ip
     * @return
     */
    @MethodNameDescrible(describle="userlog.ExploerController.Download")
    @RequestMapping(value = "/mergerDownload",method = RequestMethod.POST)
    public @ResponseBody
    ModelMap fileDown( @RequestParam String requestPath,@RequestParam String ip){
    	ModelMap result = new ModelMap();
    	HttpFileCfg httpFileCfg = new HttpFileCfg();
    	String localPath = FishCfg.getTempDir() + System.getProperty("file.separator") + "remoteDown";
    	httpFileCfg.setLocalPath(localPath);
        httpFileCfg.setRequestPath(requestPath);
        httpFileCfg.setCompress(true);
        httpFileCfg.setIpAdd(ip);
        httpFileCfg.setPort(MonitorCfg.getRemotePort());
        String localName =  DateUtils.get(new Date(),"yyyyMMddHHmmss") + ".zip";
        httpFileCfg.setLocalName(localName);
        httpFileCfg.setRetry(true);
        HttpFileRet ret = HttpFileClient.mergeDownloadFile(httpFileCfg);
        if(ret.equals(HttpFileRet.SUCCESS)){
            result.addAttribute("path", localPath);
            result.addAttribute("fileName", httpFileCfg.getLocalName());
            result.addAttribute(FishConstant.SUCCESS, true);
        }else if(ret.equals(HttpFileRet.CFG_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failParam", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.REQ_FILE_NOTEXIT)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failNotExist", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.CONN_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failConn", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.URL_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failURL", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.error", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
    
    
    /**
     * @param requestName
     * @param requestPath
     * @param ip
     * @param flag
     * @param request
     * @return
     */
    @MethodNameDescrible(describle="userlog.ExploerController.Download")
    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public @ResponseBody
    ModelMap fileDown(@RequestParam String requestName, @RequestParam String requestPath,@RequestParam String ip,@RequestParam String flag,
            WebRequest request){
        ModelMap result = new ModelMap();
        String requestPathValue = null;
        if(requestPath.lastIndexOf("\\")!=-1){
        	requestPathValue = requestPath.substring(0, requestPath.lastIndexOf("\\"));
        }else{
        	requestPathValue = requestPath.substring(0, requestPath.lastIndexOf("/"));
        }
        HttpFileCfg httpFileCfg = new HttpFileCfg();
        String localName = requestName;
        String localPath = FishCfg.getTempDir() + System.getProperty("file.separator") + "remoteDown";
        httpFileCfg.setLocalName(localName);
        httpFileCfg.setLocalPath(localPath);
        httpFileCfg.setRequestName(requestName);
        httpFileCfg.setRequestPath(requestPathValue);
        httpFileCfg.setCompress(true);
        httpFileCfg.setIpAdd(ip);
        httpFileCfg.setPort(MonitorCfg.getRemotePort());
        File file = new File(localPath+System.getProperty("file.separator")+localName);
        if("false".equals(flag)){
            //不续传下载：
            httpFileCfg.setRetry(false);
            if(file.exists()){
                file.delete();
            }
        }else{
            //续传下载：
            httpFileCfg.setRetry(true);
        }
        HttpFileRet ret = HttpFileClient.downloadFile(httpFileCfg);
        if(ret.equals(HttpFileRet.SUCCESS)){
            result.addAttribute("path", localPath);
            result.addAttribute("fileName", localName);
            result.addAttribute(FishConstant.SUCCESS, true);
        }else if(ret.equals(HttpFileRet.CFG_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failParam", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.REQ_FILE_NOTEXIT)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failNotExist", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.CONN_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failConn", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.URL_ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.failURL", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }else if(ret.equals(HttpFileRet.ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.error", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
    
    /**
     * 下载文件到浏览器端：
     * @param path
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
    @MethodNameDescrible(describle="userlog.ExploerController.Download")
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void download(@RequestParam String path, @RequestParam String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
        File file = new File(path+System.getProperty("file.separator")+fileName);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request,fileName) + "\"");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/x-msdownload;charset=UTF-8");
        OutputStream out = null;
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");
        try{
            out = response.getOutputStream();
            int len = 0;
            long contentLength = 0;
            contentLength = contentLength + randomFile.length();
            randomFile.seek(0);
            byte[] cache= new byte[1024];
            while ((len = randomFile.read(cache)) != -1){
                out.write(cache, 0, len);
                contentLength += len;
            }
            out.close();
            randomFile.close();
        }catch(Exception ex){
        	logger.error(String.format("[%s]", ex));
        }finally{
            if (out != null)
            {
                out.close();
            }
            if (randomFile != null)
            {
                randomFile.close();
            }
        }
    }

    private String getFileName(HttpServletRequest request,String name) throws Exception{
    	if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0||request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		} else {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		}
    }


    /**
     * 文件上传：
     * @param file
     * @param desPath
     * @param flag
     * @param ip
     * @param request
     * @param model
     * @param response
     * @return
     */
    @MethodNameDescrible(describle="userlog.ExploerController.Upload")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam(value = "file")MultipartFile file, @RequestParam String desPath, @RequestParam String flag, @RequestParam String ip, HttpServletRequest request, ModelMap model,HttpServletResponse response) {
        String temDir = FishCfg.getTempDir()+System.getProperty("file.separator")+"romoteUp";
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        response.setContentType("text/html;charset=UTF-8");
        if(fileSize > MAXFILESIZE){
        	String tips = messageSource.getMessage("exploer.fileUpload.failSize", new Object[]{MAXFILESIZE/SIZEOFPERM}, FishCfg.locale);
            return "{'success':false,'errors':'"+tips+"'}";//超过最大文件大小限制（最大200M）
        }
        File targetFile = new File(temDir + System.getProperty("file.separator") +fileName);
        String md5 = FileMD5.getFileMD5(targetFile);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            //通知ATM端下载上传到服务端的文件
            DownFileForm  form = new DownFileForm();
            form.setDesPath(desPath);
            form.setFileName(fileName);
            form.setSrcPath(temDir);
            form.setTermId("");
            form.setFlag(flag);
            form.setCompress(true);
            form.setMd5(md5);

            String url = MonitorCfg.getHttpUrl(request.getParameter("ip"))+"/ctr/downfile";
            form = (DownFileForm) HttpProxy.httpPost(url,form,DownFileForm.class);
            if(!form.getRet().equalsIgnoreCase(FishConstant.SUCCESS)){
            	String tips = messageSource.getMessage("exploer.fileUpload.fail", null, FishCfg.locale);
                return "{'success':false,'errors':'"+tips+"'}";
            }
        } catch (Exception e) {
        	logger.error(String.format("Exception is [%s]", e));
        	String tips = messageSource.getMessage("exploer.fileUpload.fail", null, FishCfg.locale);
            return "{'success':false,'errors':'"+tips+"'}";
        }
        return "{'success':true,'serverPath':'"+fileName+"'}";
    }

}
