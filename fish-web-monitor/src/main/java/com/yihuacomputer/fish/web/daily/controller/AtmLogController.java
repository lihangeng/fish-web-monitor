package com.yihuacomputer.fish.web.daily.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpFileRet;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.fish.api.atmlog.AtmLogCfg;
import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmCycle;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.ICustomerCycle;
import com.yihuacomputer.fish.api.atmlog.IJournalFileService;
import com.yihuacomputer.fish.api.atmlog.ITransCycle;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.daily.form.AtmLogForm;
import com.yihuacomputer.fish.web.daily.form.AtmLogTotal;

@Controller
@RequestMapping("/machine/atmLog")
public class AtmLogController {

	@Autowired
	private IAtmLogService atmLogService;

	@Autowired
	private IJournalFileService ournalFileService;
	
	
	@Autowired
	private IDeviceService deviceService;
	
	
	@Autowired
	protected MessageSource messageSource;	
	
	@Autowired
	private IAtmLogService logService;
	
	private Logger logger = LoggerFactory.getLogger(AtmLogController.class);


	@RequestMapping(value = "/getBackup", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getBackup(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpSession session) {
		ModelMap map = new ModelMap();
		IFilter filter = new Filter();
		String terminalId = request.getParameter("terminalId");
		String dateTime = request.getParameter("dateTime");
		String backupResult = request.getParameter("backupResult");
		if (terminalId != null && !terminalId.isEmpty()) {
			filter.like("terminalId", terminalId);
		}
		if (dateTime != null && !dateTime.isEmpty()) {
			filter.eq("dateTime", dateTime);
		}
		if (backupResult != null && !backupResult.isEmpty()) {
			if(backupResult.equals("SUCCESS"))
			{
				filter.eq("backupResult", BackupResult.SUCCESS);
			}
			if(backupResult.equals("BackUpError"))
			{
				filter.ne("backupResult", BackupResult.SUCCESS);
			}
			
		}		
		IPageResult<IAtmLog> atmLogResult = atmLogService.pageList(start, limit, filter);
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("total", atmLogResult.getTotal());
		map.addAttribute("data", AtmLogForm.toForms(atmLogResult.list()));
		return map;
	}

	@RequestMapping(value = "/getErrorDayBackup", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchErrorDayBackup(@RequestParam String backupDate, @RequestParam String orgId, @RequestParam int start,
			@RequestParam int limit, WebRequest request, HttpSession session) {
		ModelMap result = new ModelMap();

		IPageResult<IAtmLog> atmLogResult = atmLogService
				.pageErrorList(start, limit, backupDate, Long.parseLong(orgId));
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", atmLogResult.getTotal());
		result.addAttribute("data", AtmLogForm.toForms(atmLogResult.list()));
		return result;
	}

	@RequestMapping(value = "/getSuccessDayBackup", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchSuccessDayBackup(@RequestParam String backupDate, @RequestParam String orgId,
			@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();

		IPageResult<IAtmLog> atmLogResult = atmLogService.pageSuccessList(start, limit, backupDate,
				Long.parseLong(orgId));
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", atmLogResult.getTotal());
		result.addAttribute("data", AtmLogForm.toForms(atmLogResult.list()));
		return result;
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
	public void download(@RequestParam String deviceId, @RequestParam String dateTime, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int year;
		int month;
		String fileName = AtmLogCfg.getAtmLogFileCfg();

		fileName = StringUtils.replaceLogRule(fileName, "\\{terminalId\\}", deviceId);
		fileName = StringUtils
				.replaceLogRule(fileName, "\\{YYYYMMDD\\}", StringUtils.replaceLogRule(dateTime, "-", ""));
		fileName = StringUtils.replaceLogRule(fileName, "\\{YYYY-MM-DD\\}", dateTime);

		String path = "";
		try {
			Date date = format.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			String monthString = "";
			monthString = (month < 9) ? ("0" + String.valueOf(month + 1)) : (String.valueOf(month + 1));
			path = AtmLogCfg.getAtmAppLogDir() + FishCfg.fileSep + String.valueOf(year) + FishCfg.fileSep + monthString
					+ FishCfg.fileSep + deviceId + FishCfg.fileSep + fileName;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		File file = new File(path);

		response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, file.getName())
				+ "\"");
		response.setContentType("application/x-msdownload;charset=UTF-8");

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

	/**
	 * 文件上传：
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploade", method = RequestMethod.POST)
	public @ResponseBody
	String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		String temDir = FishCfg.getTempDir() + System.getProperty("file.separator") + "logUp";
		String fileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		response.setContentType("text/html;charset=UTF-8");
		if (fileSize > 209715200) {
			return "{'success':false,'errors':"+"'"+messageSource.getMessage("atmLog.size", null, FishCfg.locale)+"'}";// 超过最大文件大小限制（最大200M）
		}
		File targetFile = new File(temDir + System.getProperty("file.separator") + fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String tips = messageSource.getMessage("exploer.fileUpload.fail", null, FishCfg.locale);
			return "{'success':false,'errors':'"+tips+"'}";
		}
		return "{'success':true,'serverPath':'" + fileName + "'}";
	}

	/**
	 * 导出吞卡信息 生成Excel
	 *
	 * @return
	 */
	@RequestMapping(value = "/poiExcel", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap poiExcel(HttpServletRequest request, HttpServletResponse response) {
		ModelMap result = new ModelMap();
		String temDir = FishCfg.getTempDir() + System.getProperty("file.separator") + "logUp";
		String[] fileNames = request.getParameter("info").split(",");
		if (fileNames.length == 1 && fileNames[0].isEmpty()) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmLog.selectLog", null, FishCfg.locale));
		} else {
			List<File> files = new ArrayList<File>();
			for (String fileName : fileNames) {
				File file = new File(temDir + System.getProperty("file.separator") + fileName);
				files.add(file);
			}
			File sourceFile = ournalFileService.combineJournal(files);
			Iterable<IAtmCycle> atmCycles = ournalFileService.readJournalFile(sourceFile);
			List<AtmLogTotal> list = new ArrayList<AtmLogTotal>();
			for (IAtmCycle atmCycle : atmCycles) {
				if (atmCycle.getCustomers().isEmpty()) {
					AtmLogTotal atmLog = new AtmLogTotal();
					atmLog.setCashInId(atmCycle.getCashInId());
					atmLog.setAddCashTime(atmCycle.getDateTime());
					list.add(atmLog);
				} else {
					for (ICustomerCycle customerCycle : atmCycle.getCustomers()) {
						if (customerCycle.getTrans().isEmpty()) {
							AtmLogTotal atmLog = new AtmLogTotal();
							atmLog.setCashInId(atmCycle.getCashInId());
							atmLog.setAddCashTime(atmCycle.getDateTime());
							atmLog.setInsertCardTime(customerCycle.getDateTime());
							atmLog.setAccount(customerCycle.getAccount());
							list.add(atmLog);
						} else {
							for (ITransCycle transCycle : customerCycle.getTrans()) {
								AtmLogTotal atmLog = new AtmLogTotal();
								atmLog.setCashInId(atmCycle.getCashInId());
								atmLog.setAddCashTime(atmCycle.getDateTime());
								atmLog.setInsertCardTime(customerCycle.getDateTime());
								atmLog.setAccount(customerCycle.getAccount());
								atmLog.setTerminalSerial(transCycle.getTerminalSerial());
								atmLog.setHostserial(transCycle.getHostserial());
								atmLog.setTransType(transCycle.getTransType());
								atmLog.setTransAmount(transCycle.getTransAmount());
								atmLog.setHostreturn(transCycle.getHostreturn());
								atmLog.setBoxList(transCycle.getBoxList());
								list.add(atmLog);
							}
						}
					}
				}
			}
			List<AtmLogTotal> entities = new ArrayList<AtmLogTotal>();
			for (AtmLogTotal item : list) {
				entities.add(item);
			}

			// 创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(messageSource.getMessage("log.backup.excelTitle", null, FishCfg.locale));
			// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			HSSFCell cell = row.createCell((int) 0);
			cell.setCellValue(messageSource.getMessage("atmLog.cashId", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 1);
			cell.setCellValue(messageSource.getMessage("atmLog.cashTime", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 2);
			cell.setCellValue(messageSource.getMessage("atmLog.cardInsertTime", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 3);
			cell.setCellValue(messageSource.getMessage("atmLog.accNo", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 4);
			cell.setCellValue(messageSource.getMessage("atmLog.termSerialNo", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 5);
			cell.setCellValue(messageSource.getMessage("atmLog.hostSerialNo", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 6);
			cell.setCellValue(messageSource.getMessage("atmLog.transType", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 7);
			cell.setCellValue(messageSource.getMessage("atmLog.transAmt", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 8);
			cell.setCellValue(messageSource.getMessage("atmLog.hostCode", null, FishCfg.locale));
			cell.setCellStyle(style);
			cell = row.createCell((int) 9);
			cell.setCellValue(messageSource.getMessage("atmLog.cashList", null, FishCfg.locale));
			cell.setCellStyle(style);
			for (int i = 0; i < entities.size(); i++) {
				row = sheet.createRow((int) i + 1);
				AtmLogTotal atmLogTotal = entities.get(i);
				row.createCell((int) 0).setCellValue(atmLogTotal.getCashInId());
				row.createCell((int) 1).setCellValue(atmLogTotal.getAddCashTime());
				row.createCell((int) 2).setCellValue(atmLogTotal.getInsertCardTime());
				row.createCell((int) 3).setCellValue(atmLogTotal.getAccount());
				row.createCell((int) 4).setCellValue(atmLogTotal.getTerminalSerial());
				row.createCell((int) 5).setCellValue(atmLogTotal.getHostserial());
				row.createCell((int) 6).setCellValue(atmLogTotal.getTransType());
				row.createCell((int) 7).setCellValue(atmLogTotal.getTransAmount());
				row.createCell((int) 8).setCellValue(atmLogTotal.getHostreturn());
				row.createCell((int) 9).setCellValue(atmLogTotal.getBoxList());
			}
			String date = DateUtils.getDate(new Date());
			String name = "atmLogTotal" + date + ".xls";
			FileOutputStream fout = null;
			try {
				fout = new FileOutputStream(FishCfg.getTempDir()+ System.getProperty("file.separator") + name);
				try {
					wb.write(fout);
					fout.close();
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute("path", FishCfg.getTempDir() + System.getProperty("file.separator") + name);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				
			} catch (Exception e) {
					logger.error(e.getMessage());
			} finally{
				if(fout!=null){
					try {
						fout.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
				
			}
			
		}
		return result;
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
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadExl(@RequestParam String path, HttpServletResponse response) {
		response.setCharacterEncoding("gb2312");
		response.setContentType("application/x-xls");
		File file = new File(path);
		response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
		InputStream is = null;
		ServletOutputStream out = null;
		try {
			is = new FileInputStream(file);
			out = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	   /**
     * 下载备份失败的文件到服务端：
     * @param requestName
     * @param requestPath
     * @param request
     * @return
     */
	@RequestMapping(value = "/fileDownError",method = RequestMethod.POST)
    public @ResponseBody
    ModelMap fileDownError(@RequestParam String terminalId, @RequestParam String dateTime,@RequestParam String flag,
            WebRequest request){
		String ip = deviceService.get(terminalId).getIp().toString();			
		String requestPath = AtmLogCfg.getAtmLogDoc();		
		String requestName   = AtmLogCfg.getAtmLogFileCfg();
		requestName = StringUtils.replaceLogRule(requestName, "\\{terminalId\\}", terminalId);
		requestName = StringUtils.replaceLogRule(requestName, "\\{YYYYMMDD\\}", StringUtils.replaceLogRule(dateTime,"-",""));
		requestName = StringUtils.replaceLogRule(requestName, "\\{YYYY-MM-DD\\}", dateTime);		
		ModelMap result = new ModelMap();        
        HttpFileCfg httpFileCfg = new HttpFileCfg();
        String localName = requestName;
        String localPath = AtmLogCfg.getAtmAppLogDir() + FishCfg.fileSep
				+ dateTime.substring(0, 4) + FishCfg.fileSep
				+ dateTime.substring(5, 7) + FishCfg.fileSep
				+ terminalId;
        httpFileCfg.setLocalName(localName);
        httpFileCfg.setLocalPath(localPath);
        httpFileCfg.setRequestName(requestName);
        httpFileCfg.setRequestPath(requestPath);
        httpFileCfg.setCompress(true);
        httpFileCfg.setIpAdd(ip);
        httpFileCfg.setPort(MonitorCfg.getRemotePort());
        File file = new File(localPath+System.getProperty("file.separator")+localName);
        if(flag.equals("false")){
            //不续传下载：
            httpFileCfg.setRetry(false);
            if(file.exists()){
                file.delete();
            }
        }else{
            //续传下载：
            httpFileCfg.setRetry(true);
        }
        BackupResult ret = BackupResult.ERROR;
         ret = this.getBackupResult(HttpFileClient.downloadFile(httpFileCfg)); 
        if(ret.equals(BackupResult.SUCCESS)){
            result.addAttribute("path", localPath);
            result.addAttribute("fileName", localName);
            IAtmLog log = logService.getAtmLog(terminalId, dateTime);
    		log.setDoTimes(log.getDoTimes()+1);
    		log.setLastDoDate(DateUtils.getTimestamp(new Date()));
    		if(ret.equals(BackupResult.SUCCESS)){
    			log.setLogSize(getLogSize(httpFileCfg));
    		}
    		log.setBackupResult(ret);
    		logService.updateAtmLog(log);    	
            result.addAttribute(FishConstant.SUCCESS, true);
        }else if(ret.equals(BackupResult.ERROR)){
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("exploer.fileDown.error", null, FishCfg.locale));
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
	
	
	/**
	 * 获取日志文件大小
	 * @return
	 */
	private long getLogSize(HttpFileCfg cfg){
		File logFile = new File(cfg.getLocalPath()+FishCfg.fileSep+cfg.getLocalName());

		if(logFile.exists()){
			return logFile.length();
		}else{
			return 0;
		}
	}
	
	/**
	 * 判断获取文件返回码
	 * @param fileRet
	 * @return
	 */
	private BackupResult getBackupResult(HttpFileRet fileRet){

		switch(fileRet){
			case SUCCESS:{
				return BackupResult.SUCCESS;
			}
			case REQ_FILE_NOTEXIT:{
				return BackupResult.ERROR_NOLOG;
			}
			case CONN_ERROR:{
				return BackupResult.ERROR_CONNECT;
			}
			default:{
				return BackupResult.ERROR;
			}
		}
	}
}
