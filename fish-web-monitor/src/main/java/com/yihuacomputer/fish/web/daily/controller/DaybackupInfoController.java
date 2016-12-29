package com.yihuacomputer.fish.web.daily.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfo;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfoService;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IDayBackupExcuter;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;
import com.yihuacomputer.fish.api.atmlog.IDayBackupService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.daily.form.DayBackupLogForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/machine/atmLogInfo")
@ClassNameDescrible(describle="userlog.DaybackupInfoController")
public class DaybackupInfoController {

	@Autowired
	private IAtmLogInfoService atmLogInfoService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IAtmLogService atmLogService;

	@Autowired
	private IDayBackupService dayBackupService;

	@Autowired
	private IDayBackupExcuter dayBackupExcuter;
	
	
	@Autowired
	protected MessageSource messageSource;
	
	private Logger logger = LoggerFactory.getLogger(DaybackupInfoController.class);

	/**
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDayBackup", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getDayBackup(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap map = new ModelMap();
		IFilter filter = new Filter();

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String result = request.getParameter("dayBackupResult");   
		if (startDate != null && !startDate.isEmpty()) {
			filter.ge("date", startDate);
		}
		if (endDate != null && !endDate.isEmpty()) {
			filter.le("date", endDate + " 23:59:59");
		}
		if (result != null && !result.isEmpty()) {
			filter.eq("result", DayBackupResult.valueOf(result));
		}
		IPageResult<IDayBackupLog> pageResult = dayBackupService.pageList(start, limit, filter);
		Map<String,IAtmLogInfo> getBackUpInfo = atmLogInfoService.getBackUpInfo(startDate, endDate);
		
		map.addAttribute("total", pageResult.getTotal());
		map.addAttribute("data", DayBackupLogForm.toForms(pageResult.list(),getBackUpInfo));
		map.addAttribute(FishConstant.SUCCESS, true);
		return map;
	}

	/**
	 * @param request
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.DaybackupInfoController.dayBackupExcuter",hasArgs=true,argsContext="date")
	@RequestMapping(value = "/dayBackupExcuter", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap dayBackupExcuter(WebRequest request) {
		ModelMap map = new ModelMap();
		String date = request.getParameter("date");
		try {
			dayBackupExcuter.redoDayJob(date);
			map.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception e) {
			map.addAttribute(FishConstant.SUCCESS, false);
			logger.error(String.format("[%s]", e));
		}
		return map;
	}

	/**
	 * @param wRequest
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/poiExcel", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap poiExcel(WebRequest wRequest, HttpServletRequest request, HttpServletResponse response) {

		// 创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(messageSource.getMessage("dayBackupInfo.excelTitle", null, FishCfg.locale));
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.logDate", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.backUpSuccess", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.backUpFail", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.backUpTotal", null, FishCfg.locale));
		cell.setCellStyle(style);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));

		IFilter filter = request2filter(wRequest);
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		List<IAtmLogInfo> entities = atmLogInfoService.listByFilter(userSession.getOrgId(), filter);

		int rowNum = 1;
		for (IAtmLogInfo atmLogInfo : entities) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(cellValue(atmLogInfo.getOrgName()));
			row.createCell(1).setCellValue(cellValue(atmLogInfo.getBackupDate()));
			row.createCell(2).setCellValue(cellValue(atmLogInfo.getBackupSuccessNumber()));
			row.createCell(3).setCellValue(cellValue(atmLogInfo.getBackupErrorNumber()));
			row.createCell(4).setCellValue(cellValue(atmLogInfo.getTotalBackupNumber()));
		}
		String name = "AtmLogInfo.xls";

		try {
			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
			wb.write(fout);
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
		}

		File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
		this.download(file, response, "gb2312", "application/x-xls");

		return null;
	}

	/**
	 * @param backupDate
	 * @param orgId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/errorPoiExcel", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap errorPoiExcel(String backupDate, long orgId, HttpServletRequest request, HttpServletResponse response) {

		// 创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(messageSource.getMessage("dayBackupInfo.excelTitle", null, FishCfg.locale));
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.logDate", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.logResult", null, FishCfg.locale));
		cell.setCellStyle(style);

		// 解决'0001'在excel表格中点击单元格后显示为1的问题
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));

		List<IAtmLog> list = atmLogService.loadDayErrorLogs(backupDate, orgId);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			IAtmLog atmLog = list.get(i);

			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellValue(atmLog.getTerminalId()));

			cell = row.createCell(1);
			cell.setCellValue(cellValue(atmLog.getDateTime()));

			cell = row.createCell(2);
			if ("UNDO".equals(atmLog.getBackupResult().toString())) {
				cell.setCellValue(messageSource.getMessage("dayBackupInfo.undo", null, FishCfg.locale));
			} else if ("ERROR_CONNECT".equals(atmLog.getBackupResult().toString())) {
				cell.setCellValue(messageSource.getMessage("dayBackupInfo.errorConnect", null, FishCfg.locale));
			} else if ("ERROR_NOLOG".equals(atmLog.getBackupResult().toString())) {
				cell.setCellValue(messageSource.getMessage("dayBackupInfo.errorNoLog", null, FishCfg.locale));
			} else if ("ERROR".equals(atmLog.getBackupResult().toString())) {
				cell.setCellValue(messageSource.getMessage("dayBackupInfo.errorUnknown", null, FishCfg.locale));
			}
		}

		String date = DateUtils.getDate(new Date());
		String name = "errorAtmLog" + date + ".xls";

		try {
			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
			wb.write(fout);
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
		}

		File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
		this.download(file, response, "gb2312", "application/x-xls");

		return null;
	}

	/**
	 * @param backupDate
	 * @param orgId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/successPoiExcel", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap successPoiExcel(String backupDate, long orgId, HttpServletRequest request, HttpServletResponse response) {
		// 创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(messageSource.getMessage("dayBackupInfo.excelTitle", null, FishCfg.locale));
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.logDate", null, FishCfg.locale));
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue(messageSource.getMessage("dayBackupInfo.logResult", null, FishCfg.locale));
		cell.setCellStyle(style);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));

		List<IAtmLog> list = atmLogService.loadDaySuccessLogs(backupDate, orgId);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			IAtmLog atmLog = list.get(i);
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellValue(atmLog.getTerminalId()));

			cell = row.createCell(1);
			cell.setCellValue(cellValue(atmLog.getDateTime()));

			cell = row.createCell(2);
			if (atmLog.getBackupResult().toString().equals(FishConstant.SUCCESS)) {
				cell.setCellValue(messageSource.getMessage("dayBackupInfo.success", null, FishCfg.locale));
			}

		}
		String date = DateUtils.getDate(new Date());
		String name = "successAtmLog" + date + ".xls";

		try {
			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
			wb.write(fout);
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
		}

		File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
		this.download(file, response, "gb2312", "application/x-xls");

		return null;
	}

	/**
	 * 下载文件
	 *
	 * @param file
	 *            文件
	 * @param response
	 *            请求响应
	 * @param encoding
	 *            编码
	 * @param contentType
	 *            头信息
	 */
	private void download(File file, HttpServletResponse response, String encoding, String contentType) {
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment;filename=" + file.getName());

		// response.setHeader("charset", "UTF-8");

		// OutputStream os = null;
		InputStream is = null;
		// InputStreamReader isr = null;
		// OutputStreamWriter osw = null;
		ServletOutputStream out = null;
		try {
			is = new FileInputStream(file);
			// osw = new OutputStreamWriter(os, encoding);

			out = response.getOutputStream();
			// os = response.getOutputStream();

			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			// osa.close();
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}

		}
	}

	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
					continue;
				} else if ("backupDate".equals(name)) {
					filter.eq("appLogs.backupDate", value);
				} else if ("orgId".equals(name)) {
					String orgFlag = orgService.get(value).getOrgFlag();
					filter.like("device.organization.orgFlag", orgFlag);
				} 
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

	private String cellValue(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			return obj.toString();
		} else if (obj instanceof Date) {
			return DateUtils.getDate((Date) obj);
		} else if (obj instanceof Integer || obj instanceof Long || obj instanceof Double) {
			return String.valueOf(obj.toString());
		}
		return obj.toString();
	}

}
