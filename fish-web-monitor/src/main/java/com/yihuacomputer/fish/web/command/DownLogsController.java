package com.yihuacomputer.fish.web.command;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpFileRet;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.AtmLogCfg;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;

/**
 * 提取电子日志.
 *
 * @author shixiaolong
 *
 */
@Controller
@RequestMapping("/agent/downLogs")
public class DownLogsController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(DownLogsController.class);

    @Autowired
    private IDeviceService deviceService;

    /**
     * 下载文件到服务端：
     *
     * @param requestName
     * @param requestPath
     * @param request
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap fileDown(@RequestParam String ip, @RequestParam String code, WebRequest request) {
        ModelMap result = new ModelMap();
        String requestPath = AtmLogCfg.getAtmLogDoc();
        String localName = getLocalName(code);

        HttpFileCfg httpFileCfg = new HttpFileCfg();
        String localPath = FishCfg.getTempDir();
        httpFileCfg.setLocalName(localName);
        httpFileCfg.setLocalPath(localPath);
        httpFileCfg.setRequestName(localName);
        httpFileCfg.setRequestPath(requestPath);
        httpFileCfg.setRetry(true);
        httpFileCfg.setCompress(true);
        httpFileCfg.setIpAdd(ip);
        httpFileCfg.setPort(MonitorCfg.getRemotePort());
        File file = new File(localPath + System.getProperty("file.separator") + localName);
        if (file.exists()) {
            if (file.delete()) {
                HttpFileRet ret = HttpFileClient.downloadFile(httpFileCfg);
                if (ret.equals(HttpFileRet.SUCCESS)) {
                    result.addAttribute("path", localPath);
                    result.addAttribute("fileName", localName);
                    result.addAttribute(FishConstant.SUCCESS, true);
                }
                else {
                    result.addAttribute(FishConstant.SUCCESS, false);
                }
            }
        }
        else {
            HttpFileRet ret = HttpFileClient.downloadFile(httpFileCfg);
            if (ret.equals(HttpFileRet.SUCCESS)) {
                result.addAttribute("path", localPath);
                result.addAttribute("fileName", localName);
                result.addAttribute(FishConstant.SUCCESS, true);
            }
            else {
                result.addAttribute(FishConstant.SUCCESS, false);
                if(ret.equals(HttpFileRet.REQ_FILE_NOTEXIT)){
                    result.addAttribute(FishConstant.ERROR_MSG,String.format("在设备[%s]上,不存在文件[%s\\%s]",code,requestPath,localName));
                }else{
                    result.addAttribute(FishConstant.ERROR_MSG,ret.getText());
                }
            }
        }
        return result;
    }

    private String getLocalName(String code) {
        IDevice device = deviceService.get(code);
        String orgCode = device.getOrganization().getCode();

        return String.format("Bizlog-%s-%s-%s.txt", orgCode, code, DateUtils.get(new Date(), "yyyyMMdd"));
    }

    /**
     * 下载文件到浏览器端：
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void download(@RequestParam String path, @RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File(path + System.getProperty("file.separator") + fileName);
        response.setHeader("Content-Disposition", "attachment; filename=" + getFileName(request, fileName));
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/x-msdownload;charset=UTF-8");
        // response.setHeader("windows-Target", "_blank");
        OutputStream out = null;
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");
        try {
            out = response.getOutputStream();
            int len = 0;
            long contentLength = 0;
            contentLength = contentLength + randomFile.length();
            randomFile.seek(0);
            byte[] cache = new byte[1024];
            while ((len = randomFile.read(cache)) != -1) {
                out.write(cache, 0, len);
                contentLength += len;
            }
        }
        catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        finally {
            if (out != null) {
                out.close();
            }
            if (randomFile != null) {
                randomFile.close();
            }
        }
    }

    private String getFileName(HttpServletRequest request, String name) throws Exception {
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            // IE浏览器
            return URLEncoder.encode(name, "UTF-8");
        }
        else {
            return new String(name.getBytes("UTF-8"), "ISO8859-1");
        }
    }

}
