package com.yihuacomputer.fish.web.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.report.engine.pdf.PdfConfig;
import com.yihuacomputer.fish.web.report.form.RunAnalysisComparator;
import com.yihuacomputer.fish.web.report.form.RunAnalysisReportForm;

@Controller
@RequestMapping("/report/runAnalysisReport")
@ClassNameDescrible(describle = "userlog.RunAnalysisReportContorller")
public class RunAnalysisReportContorller {

	private Logger logger = LoggerFactory.getLogger(RunAnalysisReportContorller.class);

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap getBackup(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpSession session) {
		ModelMap map = new ModelMap();
		String reportType = request.getParameter("reportType");
		String filePath = null;
		if (reportType == "week") {
			filePath =PdfConfig.getWeekReportDir();
		} else {
			filePath = PdfConfig.getMonthReportDir();
		}
		List<RunAnalysisReportForm> formList = new ArrayList<RunAnalysisReportForm>();
		try {
			File file = new File(filePath);
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				String fileName = subFile.getName();
				if (subFile.isFile() && fileName.toLowerCase().endsWith(".pdf")) {
					RunAnalysisReportForm runAnalysisReportForm = new RunAnalysisReportForm();
					runAnalysisReportForm.setFileName(subFile.getName());
					runAnalysisReportForm.setCreateDate(DateUtils.getDate(new Date(subFile.lastModified())));
					runAnalysisReportForm.setFileSize(subFile.length());
					runAnalysisReportForm.setReportType(reportType);
					formList.add(runAnalysisReportForm);
				}
			}
			RunAnalysisComparator comparator = new RunAnalysisComparator();
			Collections.sort(formList, comparator);
			IPageResult<RunAnalysisReportForm> runAnalysisReportResult = new PageResult<RunAnalysisReportForm>(formList, start, limit);

			map.addAttribute(FishConstant.SUCCESS, true);
			map.addAttribute("total", runAnalysisReportResult.getTotal());
			map.addAttribute("data", runAnalysisReportResult.list());
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage());
			map.addAttribute(FishConstant.SUCCESS, true);
			map.addAttribute("total", 0);
			map.addAttribute("data", formList);
			return map;
		}
	}

	/**
	 * 下载文件
	 *
	 * @param deviceId
	 *            设备的terminalId
	 * @param dateTime
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filePath = null;
		String reportType = request.getParameter("reportType");
		if (reportType == "week") {
			filePath =PdfConfig.getWeekReportDir();
		} else {
			filePath += PdfConfig.getMonthReportDir();
		}
		String fileName = request.getParameter("fileName");
		File file = null;
		try {
			String path = filePath + FishCfg.fileSep + fileName;
			file = new File(path);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, file.getName()) + "\"");
			response.setContentType("application/x-msdownload;charset=UTF-8");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			os = response.getOutputStream();
			fis = new FileInputStream(file);
			int read;
			byte[] readarray = new byte[1024];
			while ((read = fis.read(readarray)) != -1) {
				os.write(readarray, 0, read);
			}
			os.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (os != null) {
				os.close();
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
