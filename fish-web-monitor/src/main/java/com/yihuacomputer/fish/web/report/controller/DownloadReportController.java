package com.yihuacomputer.fish.web.report.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 报表下载
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping(value = "/report/downloadFile")
public class DownloadReportController {

	/**
	 * 下载文件到浏览器端：
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void download(@RequestParam String path, @RequestParam ReportTitle reportTitle, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file = new File(path);

		String type = path.substring(path.lastIndexOf("."));
		String fileName = reportTitle.getText() + type;
		response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, fileName) + "\"");
		response.setContentType("application/x-msdownload;charset=UTF-8");
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
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
		} else {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		}
	}
}
