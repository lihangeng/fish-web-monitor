package com.yihuacomputer.fish.web.cashbox.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.CashInitPlanDeviceInfoForm;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;

@Controller
@RequestMapping("/cashInitPlanDevice")
@ClassNameDescrible(describle = "userlog.DeviceCashInitPlanDetailController")
public class DeviceCashInitPlanDetailController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashInitPlanDetailController.class);

	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashInitPlanDeviceInfoList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashInit Plan detail Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		List<ICashInitPlanDeviceInfo> cashInitPlanPageResult = cashInitPlanDeviceInfoService.list(filter);
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(Long.parseLong(request.getParameter("cashInitPlanInfoId")));
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(planInfo.getOrg().getOrgFlag());
		List<CashInitPlanDeviceInfoForm> dcbirList = convert(cashInitPlanPageResult, deviceBoxInfoMap);
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, dcbirList.size());
		result.put(FishConstant.DATA, dcbirList);
		return result;
	}

	
	
	
	/**
	 *
	 * 根据条件得到设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@MethodNameDescrible(describle="userlog.deviceController.export",hasArgs=false)
	public void export(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		List<ICashInitPlanDeviceInfo> cashInitPlanPageResult = cashInitPlanDeviceInfoService.list(filter);
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(Long.parseLong(request.getParameter("cashInitPlanInfoId")));
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(planInfo.getOrg().getOrgFlag());
		List<CashInitPlanDeviceInfoForm> dcbirList = convert(cashInitPlanPageResult, deviceBoxInfoMap);
		
		String path = createExls(dcbirList, messageSource.getMessage("cashInitPlanDevice.title", new Object[]{planInfo.getOrg().getName(),planInfo.getDate()}, FishCfg.locale));

		File file = new File(path);

		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + getFileName(request, path.substring(path.lastIndexOf(File.separator)))
						+ "\"");
		response.addHeader("Content-Length", "" + file.length());
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
			logger.error(ex.getMessage());
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
	/**
	 * 获取当前可选的设备列表
	 * 
	 * @param limit
	 * @param start
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/selectableDevice", method = RequestMethod.GET)
	public @ResponseBody ModelMap seachSelectableDeviceList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search selectable device Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(Long.parseLong(request.getParameter("cashInitPlanInfoId")));
		List<CashInitPlanDeviceInfoForm> list = null;
		try {
			list = cashInitPlanDeviceInfoService.listSelectAble(planInfo,filter);
		} catch (Exception e) {
			logger.error("load selectable initplandevice failer");
			result.put(FishConstant.SUCCESS, false);
			return result;
		}
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, list.size());
		result.put(FishConstant.DATA, list);
		return result;
	}

	/**
	 * 加钞计划删除加钞设备
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.DeviceCashInitPlanDetailController.delete",hasArgs=false,urlArgs=true)
	public ModelMap delete(@PathVariable long id) {
		logger.info(" delete cashInitPlanDeviceInfo with id = " + id);
		ModelMap result = new ModelMap();
		try {
			ICashInitPlanDeviceInfo cashInitPlanDeviceInfo = cashInitPlanDeviceInfoService.get(id);
			if(null!=cashInitPlanDeviceInfo){
				ICashInitPlanInfo cashInitPlanInfo = cashInitPlanDeviceInfo.getCashInitPlanInfo();
				double amt = cashInitPlanInfo.getAmt();
				amt-=cashInitPlanDeviceInfo.getActualAmt();
				cashInitPlanDeviceInfoService.remove(cashInitPlanDeviceInfo);
				cashInitPlanInfo.setAmt(amt);
				cashInitPlanInfoService.update(cashInitPlanInfo);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			logger.error(ex.getMessage());
		}
		return result;
	}
	/**
	 * 加钞计划中确认添加新的设备
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.DeviceCashInitPlanDetailController.add",hasArgs=false)
	public @ResponseBody ModelMap addDevice(HttpServletRequest request, WebRequest webRequest) {
		logger.info("addDevice device Info List");
		ModelMap result = new ModelMap();
		String terminalIds = request.getParameter("terminalIds").substring(1);
		long cashInitPlanInfoId = Long.parseLong(request.getParameter("cashInitPlanInfoId"));
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(cashInitPlanInfoId);
		List<CashInitPlanDeviceInfoForm> list = null;
		try {
			list = cashInitPlanDeviceInfoService.listSelectAble(planInfo,new Filter());
		
			String[] terminals = terminalIds.split(",");
			Map<String, String> terminalIdMap = new HashMap<String, String>();
			for (String terminal : terminals) {
				terminalIdMap.put(terminal, terminal);
			}
			double actualAmt = planInfo.getAmt();
			for (CashInitPlanDeviceInfoForm cashInitPlanDeviceInfoForm : list) {
				String terminalId = cashInitPlanDeviceInfoForm.getTerminalId();
				if (null != terminalIdMap.get(terminalId)) {
					ICashInitPlanDeviceInfo cashInfoPlanDeviceInfo = cashInitPlanDeviceInfoService.make();
					cashInfoPlanDeviceInfo.setActualAmt(cashInitPlanDeviceInfoForm.getActualAmt());
					actualAmt += cashInitPlanDeviceInfoForm.getActualAmt();
					cashInfoPlanDeviceInfo.setAddress(cashInitPlanDeviceInfoForm.getAddress());
					cashInfoPlanDeviceInfo.setAdviceAmt(cashInitPlanDeviceInfoForm.getAdviceAmt());
					cashInfoPlanDeviceInfo.setAwayFlag(cashInitPlanDeviceInfoForm.getAwayFlagType());
					cashInfoPlanDeviceInfo.setCashInitPlanInfo(planInfo);
					cashInfoPlanDeviceInfo.setDevType(cashInitPlanDeviceInfoForm.getDevType());
					cashInfoPlanDeviceInfo.setFlag(BoxInitRuleType.getBoxInitRuleType(cashInitPlanDeviceInfoForm.getFlag()));
					cashInfoPlanDeviceInfo.setLastAmt(cashInitPlanDeviceInfoForm.getLastAmt());
					cashInfoPlanDeviceInfo.setLastDate(cashInitPlanDeviceInfoForm.getLastDate());
					cashInfoPlanDeviceInfo.setOrgName(cashInitPlanDeviceInfoForm.getOrgName());
					cashInfoPlanDeviceInfo.setTerminalId(cashInitPlanDeviceInfoForm.getTerminalId());
					cashInfoPlanDeviceInfo = cashInitPlanDeviceInfoService.save(cashInfoPlanDeviceInfo);
				}
			}
			planInfo.setAmt(actualAmt);
			cashInitPlanInfoService.update(planInfo);
			result.put(FishConstant.SUCCESS, true);
		} catch (Exception e) {
			logger.error("load selectable initplandevice failer");
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.processError", null, FishCfg.locale));
			return result;
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新设备钞箱信息
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle = "userlog.DeviceCashInitPlanDetailController.update", hasArgs = false, urlArgs = true)
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody CashInitPlanDeviceInfoForm request) {
		logger.info("update CashBox Limit Info: CashBoxInfo.id = " + id);
		request.setId(id);
		ModelMap model = new ModelMap();
		ICashInitPlanDeviceInfo cashDeviceInfo = cashInitPlanDeviceInfoService.get(id);
		if (cashDeviceInfo == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, messageSource.getMessage("deviceBoxInfo.updateNotExist", null, FishCfg.locale));

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
			return model;
		}
		cashDeviceInfo.setActualAmt(request.getActualAmt());
		ICashInitPlanInfo initPlan = cashDeviceInfo.getCashInitPlanInfo();
		initPlan.setAmt(initPlan.getAmt() + request.getActualAmt());
		try {
			cashInitPlanDeviceInfoService.update(cashDeviceInfo);
			cashInitPlanInfoService.update(initPlan);
		} catch (Exception e) {
			logger.error(String.format("add error : %s", e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA, request);
		return model;
	}

	private List<CashInitPlanDeviceInfoForm> convert(List<ICashInitPlanDeviceInfo> list, Map<String, IDeviceBoxInfo> deviceBoxInfoMap) {
		List<CashInitPlanDeviceInfoForm> formList = new ArrayList<CashInitPlanDeviceInfoForm>();
		for (ICashInitPlanDeviceInfo cashInitPlanDevice : list) {
			CashInitPlanDeviceInfoForm form = new CashInitPlanDeviceInfoForm();
			form.setActualAmt(cashInitPlanDevice.getActualAmt());
			form.setAddress(cashInitPlanDevice.getAddress());
			form.setAdviceAmt(cashInitPlanDevice.getAdviceAmt());
			form.setAwayFlag(getI18N(cashInitPlanDevice.getAwayFlag().getText()));
			form.setDevType(cashInitPlanDevice.getDevType());
			form.setFlag(cashInitPlanDevice.getFlag().getNo());
			form.setId(cashInitPlanDevice.getId());
			form.setLastAmt(cashInitPlanDevice.getLastAmt());
			form.setLastDate(cashInitPlanDevice.getLastDate());
			form.setOrgName(cashInitPlanDevice.getOrgName());
			form.setTerminalId(cashInitPlanDevice.getTerminalId());
			IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoMap.get(cashInitPlanDevice.getTerminalId());
			if (null != deviceBoxInfo) {
				form.setMaxAmt(deviceBoxInfo.getDefaultBill());
				form.setBillAmt(deviceBoxInfo.getBillValue());
				form.setCashInAmt(deviceBoxInfo.getCashInValue());
			} else {
				form.setMaxAmt(-1);
			}
			formList.add(form);
		}
		return formList;
	}

	private String getI18N(String code) {
		return messageSource.getMessage(code, null, FishCfg.locale);
	}

	private IFilter getCashInitPlanInfoFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			}
			String value = request.getParameter(name);
			if (value == null || value.isEmpty()) {
				continue;
			}
			if ("cashInitPlanInfoId".equals(name)) {
				filter.eq("planid", Long.parseLong(value));
			} else if ("terminalId".equals(name)) {
				filter.eq("terminalId", value);
			} else if ("devType".equals(name)) {
				filter.eq("devType", value);
			} else if ("orgId".equals(name)) {
				filter.eq("orgId", value);
			} else if ("awayFlag".equals(name)) {
				filter.eq("awayFlag", value);
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name) || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name) || "sort".equals(name);
	}
	
	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	private String createExls(List<CashInitPlanDeviceInfoForm> data, String sheetName) {

		String pathname = FishCfg.getTempDir() + File.separator + DateUtils.getDate(new Date()) + ".xls";

		HSSFWorkbook workBook = new HSSFWorkbook();

		HSSFSheet sheet = workBook.createSheet(sheetName);

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.terminalId"));
				
		cell = row.createCell(1);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.actualAmt"));
		
		cell = row.createCell(2);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.maxAmt"));
		
		cell = row.createCell(3);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.adviceAmt"));
		
		cell = row.createCell(4);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.devTypeName"));
		
		
		cell = row.createCell(5);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.billAmt"));
		
		cell = row.createCell(6);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.cashInAmt"));
		
		cell = row.createCell(7);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.onBankSignal"));
		
		cell = row.createCell(8);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.orgName"));

		cell = row.createCell(9);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.lastAmt"));

		cell = row.createCell(10);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.lastDate"));

		cell = row.createCell(11);
		cell.setCellValue(getEnumI18n("cashInitPlanDevice.devAddress"));
		
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		HSSFDataFormat format = workBook.createDataFormat();

		cellStyle.setDataFormat(format.getFormat("@"));

		int count = 1;
		for (CashInitPlanDeviceInfoForm planDeviceInfo : data) {
			row = sheet.createRow(count);
			count++;
			cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellValue(planDeviceInfo.getTerminalId()));

			cell = row.createCell(1);
			cell.setCellValue(cellValue(planDeviceInfo.getActualAmt()));

			cell = row.createCell(2);
			cell.setCellValue(cellValue(planDeviceInfo.getMaxAmt()));

			cell = row.createCell(3);
			cell.setCellValue(cellValue(planDeviceInfo.getAdviceAmt()));

			cell = row.createCell(4);
			cell.setCellValue(cellValue(planDeviceInfo.getDevType()));

			cell = row.createCell(5);
			cell.setCellValue(cellValue(planDeviceInfo.getBillAmt()));

			cell = row.createCell(6);
			cell.setCellValue(cellValue(planDeviceInfo.getCashInAmt()));

			cell = row.createCell(7);
			cell.setCellValue(cellValue(planDeviceInfo.getAwayFlag()));
			
			cell = row.createCell(8);
			cell.setCellValue(cellValue(planDeviceInfo.getOrgName()));
			
			cell = row.createCell(9);
			cell.setCellValue(cellValue(planDeviceInfo.getLastAmt()));
			
			cell = row.createCell(10);
			cell.setCellValue(cellValue(planDeviceInfo.getLastDate()));
			
			cell = row.createCell(11);
			cell.setCellValue(cellValue(planDeviceInfo.getAddress()));

		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pathname);
			workBook.write(fos);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
		return pathname;
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
