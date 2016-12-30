package com.yihuacomputer.fish.web.system.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.fish.web.report.controller.DownloadReportController;


/**
 * 系统帮助文档的下载：
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping("/system")
@ClassNameDescrible(describle="userlog.SystemHelpController")
public class SystemHelpController
{
	private Logger logger = LoggerFactory.getLogger(DownloadReportController.class);
	
	/**
	 * 下载文件到浏览器端：
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@MethodNameDescrible(describle="userlog.SystemHelpController.download",hasArgs=true,argsContext="fileName")
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void download(@RequestParam String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String path = FishCfg.getFishHelpDoc()+FishCfg.FILESEP+fileName;
    	File file = new File(path);
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
}
