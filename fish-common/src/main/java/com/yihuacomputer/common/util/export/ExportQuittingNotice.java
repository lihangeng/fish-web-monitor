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
//import com.yihuacomputer.fish.srcb.util.SrcbQuittingNoticeForm;
//
//public class ExportQuittingNotice {
//	public void exportQuittingNotice(List<SrcbQuittingNoticeForm> list, HttpServletResponse response) {
//
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("报亭设备列表");
//		HSSFRow row = sheet.createRow(0);
//		sheet.setColumnWidth(0, 3600);
//		sheet.setColumnWidth(1, 7200);
//		sheet.setColumnWidth(2, 4800);
//		sheet.setColumnWidth(3, 5400);
//		sheet.setColumnWidth(4, 5400);
//		sheet.setColumnWidth(5, 3600);
//		sheet.setColumnWidth(6, 3600);
//		sheet.setColumnWidth(7, 10000);
//		sheet.setColumnWidth(8, 5400);
//		sheet.setColumnWidth(9, 3600);
//		sheet.setColumnWidth(10, 5400);
//
//		// 创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("设备号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("所属机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("设备类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("停机时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("恢复时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("当前状态");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("停机类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("停机原因");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(8);
//		cell.setCellValue("设置时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(9);
//		cell.setCellValue("停机责任人");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(10);
//		cell.setCellValue("备注");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//		cellStyle.setDataFormat(format.getFormat("@"));
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		for (int i = 0; i < list.size(); i++) {
//			row = sheet.createRow(i + 1);
//			SrcbQuittingNoticeForm quittingNotice = list.get(i);
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getDeviceCode() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getDeviceCode()));
//			}
//
//			cell = row.createCell(1);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getOrgName() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getOrgName()));
//			}
//
//			cell = row.createCell(2);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getDevCatalogName() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getDevCatalogName()));
//			}
//
//			cell = row.createCell(3);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getStopTime() != null) {
//				cell.setCellValue(cellValue(DateUtils.getTimestamp(quittingNotice.getStopTime())));
//			}
//
//			cell = row.createCell(4);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getOpenTime() != null) {
//				cell.setCellValue(cellValue(DateUtils.getTimestamp(quittingNotice.getOpenTime())));
//			}
//
//			cell = row.createCell(5);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getDevStatus() != null) {
//				if ("DISABLED".equals(quittingNotice.getDevStatus().toString())) {
//					cell.setCellValue(cellValue("停用"));
//				}
//				if ("OPENING".equals(quittingNotice.getDevStatus().toString())) {
//					cell.setCellValue(cellValue("开通"));
//				}
//			}
//
//			cell = row.createCell(6);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getStopType() != null) {
//				if("0".equals(quittingNotice.getStopType().toString()))
//				{
//					cell.setCellValue(cellValue("放假"));
//				}
//				if("1".equals(quittingNotice.getStopType().toString()))
//				{
//					cell.setCellValue(cellValue("装修"));
//				}
//				if("2".equals(quittingNotice.getStopType().toString()))
//				{
//					cell.setCellValue(cellValue("停电"));
//				}
//				if("3".equals(quittingNotice.getStopType().toString()))
//				{
//					cell.setCellValue(cellValue("设备故障未修复"));
//				}
//				if("4".equals(quittingNotice.getStopType().toString()))
//				{
//					cell.setCellValue(cellValue("其他"));
//				}
//
//			}
//
//			cell = row.createCell(7);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getStopReason() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getStopReason()));
//			}
//
//			cell = row.createCell(8);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getSetTime() != null) {
//				cell.setCellValue(cellValue(DateUtils.getTimestamp(quittingNotice.getSetTime())));
//			}
//
//			cell = row.createCell(9);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getResponsibilityName() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getResponsibilityName()));
//			}
//
//			cell = row.createCell(10);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if (quittingNotice.getCarrier() != null) {
//				cell.setCellValue(cellValue(quittingNotice.getCarrier()));
//			}
//
//		}
//		String date = DateUtils.getDate(new Date());
//		String name = "quittingNotice" + date + ".xls";
//		try {
//			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
//			wb.write(fout);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
//}
