package com.yihuacomputer.fish.web.util;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author YiHua
 *
 */
public class DownFromWebUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DownFromWebUtils.class);

    /**
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static String getFileName(HttpServletRequest request, String name) throws Exception {
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            // IE浏览器
            return URLEncoder.encode(name, "UTF-8");
        }
        else {
            return new String(name.getBytes("UTF-8"), "ISO8859-1");
        }
    }

    /**
     * @param file
     * @param request
     * @param response
     * @throws Exception
     */
    public static void download(File file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, file.getName()) + "\"");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/x-msdownload;charset=UTF-8");
        response.setHeader("windows-Target", "_blank");
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
            out.close();
            randomFile.close();
        }
        catch (Exception ex) {
        	logger.error(String.format("[%s]", ex));
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

}
