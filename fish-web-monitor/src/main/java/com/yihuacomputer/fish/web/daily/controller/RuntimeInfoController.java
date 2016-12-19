package com.yihuacomputer.fish.web.daily.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
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

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.daily.form.RuntimeInfoForm;
import com.yihuacomputer.fish.web.daily.form.RuntimeInfoListForm;
import com.yihuacomputer.fish.web.daily.form.RuntimeVersionForm;
import com.yihuacomputer.fish.web.monitor.form.HardwareVersion;
import com.yihuacomputer.fish.web.monitor.form.RuntimeInfoMsg;

@Controller
@RequestMapping("/machine/runtimeInfo")
public class RuntimeInfoController {
	// 日志
	private Logger logger = LoggerFactory.getLogger(RuntimeInfoController.class);

	private final String RUNTIMEINFO_FROMDATE_PATH = "/ctr/runtimeInfo/fromDate";

	private final String RUNTIMEINFO_LASTTOTAL30_PATH = "/ctr/runtimeInfo/LastTotal30";
	
	@Autowired
	protected MessageSource messageSource;

	@RequestMapping(value = "/exportFromDate/check", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap exportFormDateCheck(@RequestParam String ip, @RequestParam String terminalId, @RequestParam String startDate, @RequestParam String endDate,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap result = new ModelMap();
		String url = MonitorCfg.getHttpUrl(ip) + this.RUNTIMEINFO_FROMDATE_PATH;
		RuntimeInfoMsg msg = new RuntimeInfoMsg();
		msg.setDate(endDate);
		msg.setLimit(getLimit(startDate,endDate));
		FileOutputStream fout = null;
		try {
			RuntimeInfoListForm form = (RuntimeInfoListForm) HttpProxy.httpPost(url, msg, RuntimeInfoListForm.class);
			if ("01".equals(form.getAppRet())) {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet(messageSource.getMessage("runtimeInfo.excleTitle", null, FishCfg.locale));

				HSSFRow row = sheet.createRow(0);
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				HSSFCell cell = row.createCell(0);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.innerSerialNo", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.errorRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashTransFailRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(8);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(9);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.otherCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(10);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.openRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(11);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.serviceRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(12);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.faultRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				HSSFSheet hardWare = wb.createSheet(messageSource.getMessage("runtimeInfo.hardWareInfo", null, FishCfg.locale));
				HSSFRow hardwarerow = hardWare.createRow(0);

				HSSFCell hardWarecell = hardwarerow.createCell(0);
				hardWarecell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				hardWarecell.setCellStyle(style);
//				Region region = new Region(0, (short) 0, 1, (short) 0);
//				hardWare.addMergedRegion(region);
				hardWare.addMergedRegion(new CellRangeAddress(0,1,0,0));

				hardWarecell = hardwarerow.createCell(1);
				hardWarecell.setCellValue(messageSource.getMessage("runtimeInfo.hardWare", null, FishCfg.locale));
				hardWarecell.setCellStyle(style);
//				hardWare.addMergedRegion(new Region(0, (short) 1, 0, (short) 4));
				hardWare.addMergedRegion(new CellRangeAddress(0,0,1,4));

				HSSFRow hardWareInfoRow = hardWare.createRow(1);
				HSSFCell hardWareInfocell = hardWareInfoRow.createCell(1);
				hardWareInfocell.setCellValue("device("+messageSource.getMessage("runtimeInfo.deviceMod", null, FishCfg.locale)+")");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(2);
				hardWareInfocell.setCellValue("SP version");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(3);
				hardWareInfocell.setCellValue("Driver version");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(4);
				hardWareInfocell.setCellValue("FW version");
				hardWarecell.setCellStyle(style);

				HSSFSheet softWare = wb.createSheet(messageSource.getMessage("runtimeInfo.softWareInfo", null, FishCfg.locale));
				HSSFRow softWareRow = softWare.createRow(0);

				HSSFCell softWareCell = softWareRow.createCell(0);
				softWareCell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				softWareCell.setCellStyle(style);
//				softWare.addMergedRegion(new Region(0, (short) 0, 1, (short) 0));
				softWare.addMergedRegion(new CellRangeAddress(0,1,0,0));

				softWareCell = softWareRow.createCell(1);
				softWareCell.setCellValue(messageSource.getMessage("runtimeInfo.softWare", null, FishCfg.locale));
				softWareCell.setCellStyle(style);
//				softWare.addMergedRegion(new Region(0, (short) 1, 0, (short) 2));
				softWare.addMergedRegion(new CellRangeAddress(0,0,1,2));

				HSSFRow softWareInfoRow = softWare.createRow(1);
				HSSFCell softWareInfoCell = softWareInfoRow.createCell(1);
				softWareInfoCell.setCellValue(messageSource.getMessage("runtimeInfo.softWareName", null, FishCfg.locale));
				softWareInfoCell.setCellStyle(style);

				softWareInfoCell = softWareInfoRow.createCell(2);
				softWareInfoCell.setCellValue(messageSource.getMessage("runtimeInfo.softWareVersion", null, FishCfg.locale));
				softWareInfoCell.setCellStyle(style);

				// String name = "RuntimeInfo" + "_" + date + "_" + limit +"_"+
				// terminalId + ".xls";
				String time = DateUtils.getTime(new Date()).replace(":", "");
				String name = FishCfg.getTempDir() + System.getProperty("file.separator") + "RuntimeInfo" + "_" + startDate + "_" + endDate + "_" + terminalId + "_" + time + ".xls";
				fout = new FileOutputStream(name);
				try {
					wb.write(fout);
					fout.close();
				} catch (Exception e) {
					logger.error(String.format("[%s]", e));
				}
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("path", name);

			} else {
				List<RuntimeInfoForm> list = form.getRuntimeInfoList();

				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet(messageSource.getMessage("runtimeInfo.excleTitle", null, FishCfg.locale));

				HSSFRow row = sheet.createRow(0);
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				HSSFCell cell = row.createCell(0);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.innerSerialNo", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.errorRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashTransFailRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(8);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(9);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.otherCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(10);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.openRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(11);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.serviceRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(12);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.faultRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				HSSFSheet hardWare = wb.createSheet(messageSource.getMessage("runtimeInfo.hardWareInfo", null, FishCfg.locale));
				HSSFRow hardwarerow = hardWare.createRow(0);

				HSSFCell hardWarecell = hardwarerow.createCell(0);
				hardWarecell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				hardWarecell.setCellStyle(style);
//				Region region = new Region(0, (short) 0, 1, (short) 0);
//				hardWare.addMergedRegion(region);
				CellRangeAddress region = new CellRangeAddress(0, 1, 0, 0);
				hardWare.addMergedRegion(region);

				hardWarecell = hardwarerow.createCell(1);
				hardWarecell.setCellValue(messageSource.getMessage("runtimeInfo.hardWare", null, FishCfg.locale));
				hardWarecell.setCellStyle(style);
//				hardWare.addMergedRegion(new Region(0, (short) 1, 0, (short) 4));
				hardWare.addMergedRegion(new CellRangeAddress(0,0,1,4));

				HSSFRow hardWareInfoRow = hardWare.createRow(1);
				HSSFCell hardWareInfocell = hardWareInfoRow.createCell(1);
				hardWareInfocell.setCellValue("device("+messageSource.getMessage("runtimeInfo.deviceMod", null, FishCfg.locale)+")");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(2);
				hardWareInfocell.setCellValue("SP version");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(3);
				hardWareInfocell.setCellValue("Driver version");
				hardWarecell.setCellStyle(style);

				hardWareInfocell = hardWareInfoRow.createCell(4);
				hardWareInfocell.setCellValue("FW version");
				hardWarecell.setCellStyle(style);

				HSSFSheet softWare = wb.createSheet(messageSource.getMessage("runtimeInfo.softWareInfo", null, FishCfg.locale));
				HSSFRow softWareRow = softWare.createRow(0);

				HSSFCell softWareCell = softWareRow.createCell(0);
				softWareCell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				softWareCell.setCellStyle(style);
//				softWare.addMergedRegion(new Region(0, (short) 0, 1, (short) 0));
				softWare.addMergedRegion(new CellRangeAddress(0,1,0,0));

				softWareCell = softWareRow.createCell(1);
				softWareCell.setCellValue(messageSource.getMessage("runtimeInfo.softWare", null, FishCfg.locale));
				softWareCell.setCellStyle(style);
//				softWare.addMergedRegion(new Region(0, (short) 1, 0, (short) 2));
				softWare.addMergedRegion(new CellRangeAddress(0,0,1,2));

				HSSFRow softWareInfoRow = softWare.createRow(1);
				HSSFCell softWareInfoCell = softWareInfoRow.createCell(1);
				softWareInfoCell.setCellValue(messageSource.getMessage("runtimeInfo.softWare", null, FishCfg.locale));
				softWareInfoCell.setCellStyle(style);

				softWareInfoCell = softWareInfoRow.createCell(2);
				softWareInfoCell.setCellValue(messageSource.getMessage("runtimeInfo.softWareVersion", null, FishCfg.locale));
				softWareInfoCell.setCellStyle(style);

				int m = 0;
				int k = 0;
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 1);
					row.createCell(0).setCellValue(list.get(i).getSerialno());
					row.createCell(1).setCellValue(list.get(i).getDate());
					row.createCell(2).setCellValue(list.get(i).getCashRefusedRate());
					row.createCell(3).setCellValue(list.get(i).getRefusedNoteCount());
					row.createCell(4).setCellValue(list.get(i).getTotalNoteCount());
					row.createCell(5).setCellValue(list.get(i).getCashErrorRate());
					row.createCell(6).setCellValue(list.get(i).getCashTransError());
					row.createCell(7).setCellValue(list.get(i).getCashTransCount());
					row.createCell(8).setCellValue(list.get(i).getCashNoteCount());
					row.createCell(9).setCellValue(list.get(i).getOtherTransCount());
					row.createCell(10).setCellValue(list.get(i).getCustomRate());
					row.createCell(11).setCellValue(list.get(i).getAdminRate());
					row.createCell(12).setCellValue(list.get(i).getStopRate());

					List<HardwareVersion> hardWareList = list.get(i).getListHardwareVersion();
					List<RuntimeVersionForm> runtimeVersionList = list.get(i).getListSoftwareVersion();

					if (i == 0) {
						/**
						 * 插入硬件信息
						 */
						if (hardWareList != null) { // 判断硬件版本信息不为空的时候
							for (int j = 0; j < hardWareList.size(); j++) {
								hardWareInfoRow = hardWare.createRow(j + 2);
								if (j == 0) {
									hardWareInfoRow.createCell(0).setCellValue(list.get(i).getDate());
//									hardWare.addMergedRegion(new Region(j + 2, (short) 0, j + 2 + hardWareList.size() - 1, (short) 0));
									hardWare.addMergedRegion(new CellRangeAddress(j + 2, j + 2 + hardWareList.size() - 1, 0, 0));
								}
								hardWareInfoRow.createCell(1).setCellValue(hardWareList.get(j).getDevice());
								hardWareInfoRow.createCell(2).setCellValue(hardWareList.get(j).getSpVersion());
								hardWareInfoRow.createCell(3).setCellValue(hardWareList.get(j).getDriverVersion());
								hardWareInfoRow.createCell(4).setCellValue(hardWareList.get(j).getFwVersion());
								m = hardWareList.size() + 2;
							}
						}

						/**
						 * 插入软件的信息
						 */
						if (runtimeVersionList != null) { // 判断软件版本不为空的情况
							for (int n = 0; n < runtimeVersionList.size(); n++) {
								softWareInfoRow = softWare.createRow(n + 2);
								if (n == 0) {
									softWareInfoRow.createCell(0).setCellValue(list.get(i).getDate());
//									softWare.addMergedRegion(new Region(n + 2, (short) 0, n + 2 + runtimeVersionList.size() - 1, (short) 0));
									softWare.addMergedRegion(new CellRangeAddress(n + 2, n + 2 + runtimeVersionList.size() - 1, 0, 0));
								}
								softWareInfoRow.createCell(1).setCellValue(runtimeVersionList.get(n).getName());
								softWareInfoRow.createCell(2).setCellValue(runtimeVersionList.get(n).getVersion());
								k = runtimeVersionList.size() + 2;
							}

						}

					}

					if (i > 0) {
						/**
						 * 插入硬件信息
						 */
						if (hardWareList != null) {
							for (int j = 0; j < hardWareList.size(); j++) {
								hardWareInfoRow = hardWare.createRow(m);
								if (j == 0) {
									hardWareInfoRow.createCell(0).setCellValue(list.get(i).getDate());
//									hardWare.addMergedRegion(new Region(m, (short) 0, m + hardWareList.size() - 1, (short) 0));
									hardWare.addMergedRegion(new CellRangeAddress(m, m + hardWareList.size() - 1, 0, 0 ));
								}
								hardWareInfoRow.createCell(1).setCellValue(hardWareList.get(j).getDevice());
								hardWareInfoRow.createCell(2).setCellValue(hardWareList.get(j).getSpVersion());
								hardWareInfoRow.createCell(3).setCellValue(hardWareList.get(j).getDriverVersion());
								hardWareInfoRow.createCell(4).setCellValue(hardWareList.get(j).getFwVersion());
								m = m + 1;
							}
						}

						/**
						 * 插入软件信息
						 */
						if (runtimeVersionList != null) {
							for (int n = 0; n < runtimeVersionList.size(); n++) {
								softWareInfoRow = softWare.createRow(k);
								if (n == 0) {
									softWareInfoRow.createCell(0).setCellValue(list.get(i).getDate());
//									softWare.addMergedRegion(new Region(k, (short) 0, k + runtimeVersionList.size() - 1, (short) 0));
									softWare.addMergedRegion(new CellRangeAddress(k, k + runtimeVersionList.size() - 1, 0, 0));
								}
								softWareInfoRow.createCell(1).setCellValue(runtimeVersionList.get(n).getName());
								softWareInfoRow.createCell(2).setCellValue(runtimeVersionList.get(n).getVersion());
								k = k + 1;
							}
						}

					}

				}
				// String name = "RuntimeInfo" + "_" + date + "_" + limit +
				// terminalId + ".xls";
				String time = DateUtils.getTime(new Date()).replace(":", "");
				String name = FishCfg.getTempDir() + System.getProperty("file.separator") + "RuntimeInfo" + "_" + startDate + "_" + endDate + "_" + terminalId + "_" + time + ".xls";
				fout = new FileOutputStream(name);
				try {
					wb.write(fout);
					fout.close();
				} catch (Exception e) {
					logger.error(String.format("[%s]", e));
				}
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("path", name);

			}
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("msg", e.getMessage());
			return result;
		}finally{
			if(fout!=null){
				try {
					fout.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}
			
		}
		return result;
	}

	private int getLimit(String endDate, String startDate) {
	    /*Calendar d1 = Calendar.getInstance();*/
	    Date start = DateUtils.getDate(startDate);
	    Date end = DateUtils.getDate(endDate);
	    long times = end.getTime() - start.getTime();
        Long diff =  Long.valueOf(times/(1000*60*60*24L));
        return diff.intValue();
    }

    @RequestMapping(value = "/exportFromDate/download", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap exportFormDateDownload(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) {
		File file = new File(path);
		this.download(file, response, "gb2312", "application/x-xls");
		return null;
	}

	@RequestMapping(value = "/exportLast30/check", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap exportLast30Check(@RequestParam String ip, @RequestParam String terminalId, HttpServletRequest request, HttpServletResponse response) {
		ModelMap result = new ModelMap();
		String url = MonitorCfg.getHttpUrl(ip) + this.RUNTIMEINFO_LASTTOTAL30_PATH;
		FileOutputStream fout = null;
		try {
			RuntimeInfoForm runtimeInfoForm = (RuntimeInfoForm) HttpProxy.httpGet(url, RuntimeInfoForm.class);
			if ("01".equals(runtimeInfoForm.getAppRet())) {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet(messageSource.getMessage("runtimeInfo.excle30DaysTitle", null, FishCfg.locale));
				HSSFRow row = sheet.createRow(0);
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				HSSFCell cell = row.createCell(0);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.innerSerialNo", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.errorRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashTransFailRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(8);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(9);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.otherCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(10);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.openRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(11);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.serviceRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(12);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.faultRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(13);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.deviceMod", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(14);
				cell.setCellValue("SP version");
				cell.setCellStyle(style);

				cell = row.createCell(15);
				cell.setCellValue("Driver version");
				cell.setCellStyle(style);

				cell = row.createCell(16);
				cell.setCellValue("FW version");
				cell.setCellStyle(style);

				cell = row.createCell(17);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.softWareName", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(18);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.softWareVersion", null, FishCfg.locale));
				cell.setCellStyle(style);

				String date = DateUtils.getDate(new Date());
				String time = DateUtils.getTime(new Date()).replace(":", "");
				String name = FishCfg.getTempDir() + System.getProperty("file.separator") + "RuntimeInfoLast30" + "_" + terminalId + "_" + date + "_" + time +".xls";

				try {
					fout = new FileOutputStream(name);
					wb.write(fout);
					fout.close();
				} catch (Exception e) {
					logger.error(String.format("[%s]", e));
				}
				// File file = new File(name);
				// this.download(file, response, "gb2312", "application/x-xls");
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("path", name);
				return result;

			} else {
				List<HardwareVersion> listHardwareVersion = runtimeInfoForm.getListHardwareVersion();
				List<RuntimeVersionForm> listRuntimeVersion = runtimeInfoForm.getListSoftwareVersion();

				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet(messageSource.getMessage("runtimeInfo.excle30DaysTitle", null, FishCfg.locale));
				HSSFRow row = sheet.createRow(0);
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				HSSFCell cell = row.createCell(0);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.innerSerialNo", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.refuseCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.errorRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cashTransFailRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(8);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.cimCashCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(9);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.otherCount", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(10);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.openRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(11);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.serviceRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(12);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.faultRate", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(13);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.deviceMod", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(14);
				cell.setCellValue("SP version");
				cell.setCellStyle(style);

				cell = row.createCell(15);
				cell.setCellValue("Driver version");
				cell.setCellStyle(style);

				cell = row.createCell(16);
				cell.setCellValue("FW version");
				cell.setCellStyle(style);

				cell = row.createCell(17);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.softWareName", null, FishCfg.locale));
				cell.setCellStyle(style);

				cell = row.createCell(18);
				cell.setCellValue(messageSource.getMessage("runtimeInfo.softWareVersion", null, FishCfg.locale));
				cell.setCellStyle(style);
				/**
				 * 比较size 拼凑成excel表格
				 */
				if (listHardwareVersion != null && listRuntimeVersion != null) { // 硬件版本和软件版本都不为空的情况
					if (listHardwareVersion.size() < listRuntimeVersion.size()) {
						for (int i = 0; i < listRuntimeVersion.size(); i++) {
							row = sheet.createRow(i + 1);
							if (i == 0) {
								row.createCell(0).setCellValue(runtimeInfoForm.getSerialno());
								row.createCell(1).setCellValue(runtimeInfoForm.getDate());
								row.createCell(2).setCellValue(runtimeInfoForm.getCashRefusedRate());
								row.createCell(3).setCellValue(runtimeInfoForm.getRefusedNoteCount());
								row.createCell(4).setCellValue(runtimeInfoForm.getTotalNoteCount());
								row.createCell(5).setCellValue(runtimeInfoForm.getCashErrorRate());
								row.createCell(6).setCellValue(runtimeInfoForm.getCashTransError());
								row.createCell(7).setCellValue(runtimeInfoForm.getCashTransCount());
								row.createCell(8).setCellValue(runtimeInfoForm.getCashNoteCount());
								row.createCell(9).setCellValue(runtimeInfoForm.getOtherTransCount());
								row.createCell(10).setCellValue(runtimeInfoForm.getCustomRate());
								row.createCell(11).setCellValue(runtimeInfoForm.getAdminRate());
								row.createCell(12).setCellValue(runtimeInfoForm.getStopRate());
							}
							if (i <= listHardwareVersion.size() - 1) {
								HardwareVersion item = listHardwareVersion.get(i);
								row.createCell(13).setCellValue(item.getDevice());
								row.createCell(14).setCellValue(item.getSpVersion());
								row.createCell(15).setCellValue(item.getDriverVersion());
								row.createCell(16).setCellValue(item.getFwVersion());
							}

							RuntimeVersionForm entity = listRuntimeVersion.get(i);
							row.createCell(17).setCellValue(entity.getName());
							row.createCell(18).setCellValue(entity.getVersion());
						}
					}

					if (listHardwareVersion.size() == listRuntimeVersion.size()) {
						for (int i = 0; i < listHardwareVersion.size(); i++) {
							row = sheet.createRow(i + 1);
							if (i == 0) {
								row.createCell(0).setCellValue(runtimeInfoForm.getSerialno());
								row.createCell(1).setCellValue(runtimeInfoForm.getDate());
								row.createCell(2).setCellValue(runtimeInfoForm.getCashRefusedRate());
								row.createCell(3).setCellValue(runtimeInfoForm.getRefusedNoteCount());
								row.createCell(4).setCellValue(runtimeInfoForm.getTotalNoteCount());
								row.createCell(5).setCellValue(runtimeInfoForm.getCashErrorRate());
								row.createCell(6).setCellValue(runtimeInfoForm.getCashTransError());
								row.createCell(7).setCellValue(runtimeInfoForm.getCashTransCount());
								row.createCell(8).setCellValue(runtimeInfoForm.getCashNoteCount());
								row.createCell(9).setCellValue(runtimeInfoForm.getOtherTransCount());
								row.createCell(10).setCellValue(runtimeInfoForm.getCustomRate());
								row.createCell(11).setCellValue(runtimeInfoForm.getAdminRate());
								row.createCell(12).setCellValue(runtimeInfoForm.getStopRate());
							}
							HardwareVersion item = listHardwareVersion.get(i);
							row.createCell(13).setCellValue(item.getDevice());
							row.createCell(14).setCellValue(item.getSpVersion());
							row.createCell(15).setCellValue(item.getDriverVersion());
							row.createCell(16).setCellValue(item.getFwVersion());

							RuntimeVersionForm entity = listRuntimeVersion.get(i);
							row.createCell(17).setCellValue(entity.getName());
							row.createCell(18).setCellValue(entity.getVersion());
						}
					}

					if (listHardwareVersion.size() > listRuntimeVersion.size()) {
						for (int i = 0; i < listHardwareVersion.size(); i++) {
							row = sheet.createRow(i + 1);
							if (i == 0) {
								row.createCell(0).setCellValue(runtimeInfoForm.getSerialno());
								row.createCell(1).setCellValue(runtimeInfoForm.getDate());
								row.createCell(2).setCellValue(runtimeInfoForm.getCashRefusedRate());
								row.createCell(3).setCellValue(runtimeInfoForm.getRefusedNoteCount());
								row.createCell(4).setCellValue(runtimeInfoForm.getTotalNoteCount());
								row.createCell(5).setCellValue(runtimeInfoForm.getCashErrorRate());
								row.createCell(6).setCellValue(runtimeInfoForm.getCashTransError());
								row.createCell(7).setCellValue(runtimeInfoForm.getCashTransCount());
								row.createCell(8).setCellValue(runtimeInfoForm.getCashNoteCount());
								row.createCell(9).setCellValue(runtimeInfoForm.getOtherTransCount());
								row.createCell(10).setCellValue(runtimeInfoForm.getCustomRate());
								row.createCell(11).setCellValue(runtimeInfoForm.getAdminRate());
								row.createCell(12).setCellValue(runtimeInfoForm.getStopRate());
							}
							if (i <= listRuntimeVersion.size() - 1) {
								RuntimeVersionForm entity = listRuntimeVersion.get(i);
								row.createCell(17).setCellValue(entity.getName());
								row.createCell(18).setCellValue(entity.getVersion());
							}
							HardwareVersion item = listHardwareVersion.get(i);
							row.createCell(13).setCellValue(item.getDevice());
							row.createCell(14).setCellValue(item.getSpVersion());
							row.createCell(15).setCellValue(item.getDriverVersion());
							row.createCell(16).setCellValue(item.getFwVersion());

						}
					}
				} else if (listHardwareVersion == null && listRuntimeVersion != null) {
					for (int i = 0; i < listRuntimeVersion.size(); i++) {
						row = sheet.createRow(i + 1);
						if (i == 0) {
							row.createCell(0).setCellValue(runtimeInfoForm.getSerialno());
							row.createCell(1).setCellValue(runtimeInfoForm.getDate());
							row.createCell(2).setCellValue(runtimeInfoForm.getCashRefusedRate());
							row.createCell(3).setCellValue(runtimeInfoForm.getRefusedNoteCount());
							row.createCell(4).setCellValue(runtimeInfoForm.getTotalNoteCount());
							row.createCell(5).setCellValue(runtimeInfoForm.getCashErrorRate());
							row.createCell(6).setCellValue(runtimeInfoForm.getCashTransError());
							row.createCell(7).setCellValue(runtimeInfoForm.getCashTransCount());
							row.createCell(8).setCellValue(runtimeInfoForm.getCashNoteCount());
							row.createCell(9).setCellValue(runtimeInfoForm.getOtherTransCount());
							row.createCell(10).setCellValue(runtimeInfoForm.getCustomRate());
							row.createCell(11).setCellValue(runtimeInfoForm.getAdminRate());
							row.createCell(12).setCellValue(runtimeInfoForm.getStopRate());
						}
						RuntimeVersionForm entity = listRuntimeVersion.get(i);
						row.createCell(17).setCellValue(entity.getName());
						row.createCell(18).setCellValue(entity.getVersion());
					}
				} else if (listHardwareVersion != null && listRuntimeVersion == null) {
					for (int i = 0; i < listHardwareVersion.size(); i++) {
						row = sheet.createRow(i + 1);
						if (i == 0) {
							row.createCell(0).setCellValue(runtimeInfoForm.getSerialno());
							row.createCell(1).setCellValue(runtimeInfoForm.getDate());
							row.createCell(2).setCellValue(runtimeInfoForm.getCashRefusedRate());
							row.createCell(3).setCellValue(runtimeInfoForm.getRefusedNoteCount());
							row.createCell(4).setCellValue(runtimeInfoForm.getTotalNoteCount());
							row.createCell(5).setCellValue(runtimeInfoForm.getCashErrorRate());
							row.createCell(6).setCellValue(runtimeInfoForm.getCashTransError());
							row.createCell(7).setCellValue(runtimeInfoForm.getCashTransCount());
							row.createCell(8).setCellValue(runtimeInfoForm.getCashNoteCount());
							row.createCell(9).setCellValue(runtimeInfoForm.getOtherTransCount());
							row.createCell(10).setCellValue(runtimeInfoForm.getCustomRate());
							row.createCell(11).setCellValue(runtimeInfoForm.getAdminRate());
							row.createCell(12).setCellValue(runtimeInfoForm.getStopRate());
						}
						HardwareVersion item = listHardwareVersion.get(i);
						row.createCell(13).setCellValue(item.getDevice());
						row.createCell(14).setCellValue(item.getSpVersion());
						row.createCell(15).setCellValue(item.getDriverVersion());
						row.createCell(16).setCellValue(item.getFwVersion());
					}
				}

				String date = DateUtils.getDate(new Date());
				String time = DateUtils.getTime(new Date()).replace(":", "");
				String name = FishCfg.getTempDir() + System.getProperty("file.separator") + "RuntimeInfoLast30" + "_" + terminalId + "_" + date + "_" + time + ".xls";
				fout = new FileOutputStream(name);
				try {
					wb.write(fout);
					fout.close();
				} catch (Exception e) {
					logger.error(String.format("[%s]", e));
				}

				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("path", name);
				return result;
			}
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("msg", e.getMessage());
			return result;
		}finally{
			if(fout!=null){
				try {
					fout.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}
			
		}
	}

	@RequestMapping(value = "/exportLast30/download", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap exportLast30Download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) {
		File file = new File(path);
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
}
