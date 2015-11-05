//package com.yihuacomputer.common.util.export;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//
//import com.yihuacomputer.common.FishCfg;
//import com.yihuacomputer.common.util.DateUtils;
//import com.yihuacomputer.fish.api.fault.FaultStatus;
//import com.yihuacomputer.fish.fault.srcb.SrcbCaseFault;
//
//public class ExportCaseFault {
//	public void exportCaseFault(List<SrcbCaseFault> list, HttpServletResponse response) {
//
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("故障列表");
//		HSSFRow row = sheet.createRow(0);
//		sheet.setColumnWidth(0, 3600);
//		sheet.setColumnWidth(1, 3600);
//		sheet.setColumnWidth(2, 4800);
//		sheet.setColumnWidth(3, 4800);
//		sheet.setColumnWidth(4, 3600);
//		sheet.setColumnWidth(5, 3600);
//		sheet.setColumnWidth(6, 6000);
//		sheet.setColumnWidth(7, 6000);
//		sheet.setColumnWidth(8, 3600);
//		sheet.setColumnWidth(9, 3600);
//		// 创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("设备号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("所属机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("机构备注");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("故障模块");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("故障分类");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("厂商故障码");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("故障开始时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("故障关闭时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(8);
//		cell.setCellValue("持续时长");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(9);
//		cell.setCellValue("故障状态");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//		cellStyle.setDataFormat(format.getFormat("@"));
//		for (int i = 0; i < list.size(); i++) {
//			row = sheet.createRow(i + 1);
//			SrcbCaseFault fault = list.get(i);
//
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(fault.getTerminalId()));
//
//			cell = row.createCell(1);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(fault.getOrg()));
//
//			cell = row.createCell(2);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(fault.getCarrier()));
//
//			cell = row.createCell(3);
//			cell.setCellStyle(cellStyle);
//			if (null == fault.getDevMod()) {
//				cell.setCellValue(cellValue(""));
//			} else {
//				cell.setCellValue(cellValue(fault.getDevMod().getText()));
//			}
//
//			cell = row.createCell(4);
//			cell.setCellStyle(cellStyle);
//			if (null == fault.getFaultClassify()) {
//				cell.setCellValue(cellValue(""));
//			} else {
//				cell.setCellValue(cellValue(fault.getFaultClassify().getClassifyName()));
//			}
//
//			cell = row.createCell(5);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(fault.getVendorHwCode()));
//
//			cell = row.createCell(6);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(DateUtils.getTimestamp(fault.getFaultTime())));
//
//			cell = row.createCell(7);
//			cell.setCellStyle(cellStyle);
//			long closeTime = 0;
//			if (fault.getClosedTime() != null) {
//				closeTime = (fault.getClosedTime().getTime()) / 1000;
//				cell.setCellValue(cellValue(DateUtils.getTimestamp(fault.getClosedTime())));
//			} else {
//				cell.setCellValue("");
//			}
//			cell = row.createCell(8);
//			cell.setCellStyle(cellStyle);
//			String duration;
//			if (closeTime != 0) {
//				long faultTime = (fault.getFaultTime().getTime()) / 1000;
//				long time = closeTime - faultTime;
//				duration = getTimes(time);
//			} else {
//				duration = "";
//			}
//			cell.setCellValue(cellValue(duration));
//			cell = row.createCell(9);
//			cell.setCellStyle(cellStyle);
//			if (fault.getFaultStatus().equals(FaultStatus.OPEN)) {
//				cell.setCellValue("未关闭");
//			} else if (fault.getFaultStatus().equals(FaultStatus.CLOSED)) {
//				cell.setCellValue("已关闭");
//			}
//		}
//
//		String date = DateUtils.getDate(new Date());
//		String name = "caseFault_" + date + ".xls";
//		try {
//			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
//			wb.write(fout);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
//		this.download(file, response, "gb2312", "application/x-xls");
//	}
//
//	private void download(File file, HttpServletResponse response, String encoding, String contentType) {
//		response.setCharacterEncoding(encoding);
//		response.setContentType(contentType);
//		response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
//		InputStream is = null;
//		ServletOutputStream out = null;
//		try {
//			is = new FileInputStream(file);
//			out = response.getOutputStream();
//			int len = 0;
//			byte[] buffer = new byte[1024];
//			while ((len = is.read(buffer)) != -1) {
//				out.write(buffer, 0, len);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	private String cellValue(Object obj) {
//		if (obj == null) {
//			return "";
//		}
//		if (obj instanceof String) {
//			return obj.toString();
//		} else if (obj instanceof Date) {
//			return DateUtils.getDate((Date) obj);
//		} else if (obj instanceof Integer || obj instanceof Long || obj instanceof Double) {
//			return String.valueOf(obj.toString());
//		}
//		return obj.toString();
//	}
//
//	private String getTimes(long times) {
//		StringBuffer sb = new StringBuffer("");
//		String t1 = (times / 60 / 60) + "";
//		String t2 = (times / 60 % 60) + "";
//		String t3 = (times % 60) + "";
//		sb.append(t1.length() == 1 ? "0" + t1 : t1).append(":");
//		sb.append(t2.length() == 1 ? "0" + t2 : t2).append(":");
//
//		sb.append(t3.length() == 1 ? "0" + t3 : t3);
//		return sb.toString();
//	}
//}
