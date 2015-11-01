package com.yihuacomputer.common.util.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.util.DateUtils;

public class ExportOpenPlan {
//	public void exportOpenPlan(List<ExportOpenPlan> list, List <Long>plan_Dev, HttpServletResponse response) {
//
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("开机方案列表");
//		HSSFRow row = sheet.createRow(0);
//		sheet.setColumnWidth(0, 3600);
//		sheet.setColumnWidth(1, 3600);
//		sheet.setColumnWidth(2, 3600);
//		sheet.setColumnWidth(3, 3600);
//		sheet.setColumnWidth(4, 3600);
//		sheet.setColumnWidth(5, 3600);
//		sheet.setColumnWidth(6, 3600);
//		sheet.setColumnWidth(7, 4800);
//		sheet.setColumnWidth(8, 5400);
//		sheet.setColumnWidth(9, 5400);
//		sheet.setColumnWidth(10, 3600);
//		sheet.setColumnWidth(11, 5400);
//		sheet.setColumnWidth(12, 5400);
//		sheet.setColumnWidth(13, 10000);
//
//		// 创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("设备号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("设备IP");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("所属机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("设备型号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("设备类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("方案名称");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("方案类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("设备数量");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(8);
//		cell.setCellValue("状态");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(9);
//		cell.setCellValue("有效开始时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(10);
//		cell.setCellValue("有效结束时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(11);
//		cell.setCellValue("备注");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(12);
//		cell.setCellValue("创建时间");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(13);
//		cell.setCellValue("方案详情");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//		cellStyle.setDataFormat(format.getFormat("@"));
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//		for (int i = 0; i < list.size(); i++) {
//			row = sheet.createRow(i + 1);
//			ExportOpenPlanForm  openPlan = list.get(i);
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getTerminalId()!= null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getTerminalId()));
//
//			}
//
//			cell = row.createCell(1);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getIp()!= null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getIp()));
//
//			}
//
//			cell = row.createCell(2);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getOrgName()!= null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getOrgName()));
//
//			}
//
//			cell = row.createCell(3);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getDevType()!= null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getDevType()));
//
//			}
//
//			cell = row.createCell(4);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getDevCatalog()!= null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getDevCatalog()));
//
//			}
//
//			cell = row.createCell(5);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getName() != null)
//			{
//			  cell.setCellValue(cellValue(openPlan.getName()));
//
//			}
//
//			cell = row.createCell(6);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getPlanType() != null)
//			{
//				if(("DATE").equals(openPlan.getPlanType().toString()))
//				{
//				   cell.setCellValue(cellValue("日期"));
//				}
//				if(("WEEK").equals(openPlan.getPlanType().toString()))
//				{
//					cell.setCellValue(cellValue("星期"));
//				}
//			}
//
//			cell = row.createCell(7);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//		    cell.setCellValue(cellValue(openPlan.getDeviceCount()));
//
//			cell = row.createCell(8);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getPlanStateType() != null)
//			{
//				cell.setCellValue(cellValue(openPlan.getPlanStateType()));
//			}
//
//			cell = row.createCell(9);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getStartDate() != null)
//			{
//				cell.setCellValue(cellValue(openPlan.getStartDate()));
//			}
//
//			cell = row.createCell(10);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getEndDate() != null)
//			{
//				cell.setCellValue(cellValue(openPlan.getEndDate()));
//			}
//
//			cell = row.createCell(11);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getDesc() != null)
//			{
//				cell.setCellValue(cellValue(openPlan.getDesc()));
//			}
//
//			cell = row.createCell(12);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getCreateDateTime()!= null)
//			{
//				cell.setCellValue(cellValue(openPlan.getCreateDateTime()));
//			}
//
//			cell = row.createCell(13);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cellStyle.setWrapText(true);
//			cell.setCellStyle(cellStyle);
//			if(openPlan.getOpenPlanDetails()!= null)
//			{
//				cell.setCellValue(cellValue(openPlan.getOpenPlanDetails()));
//			}
//		}
//      for(int i = 5; i<=13; i++)
//        {
//        	long startRow = 1;
//        	long endRow = 1;
//        	for(Long number : plan_Dev)
//        	{
//        	    long dev_number = number;
//        	    if(dev_number > 1)
//        	    {
//        	      endRow = endRow + dev_number -1;
//        	      CellRangeAddress range = new CellRangeAddress((int)startRow,(int)endRow,i,i);
//        	      sheet.addMergedRegion(range);
//        	      endRow++;
//        	      startRow = endRow;
//        	    }
//        	    else
//        	    {
//        	      endRow++;
//          	      startRow = endRow;
//        	    }
//
//        	}
//
//        }
//		String date = DateUtils.getDate(new Date());
//		String name = "openPlan" + date + ".xls";
//		try {
//			FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
//			wb.write(fout);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
//		this.download(file, response, "gb2312", "application/x-xls");
//	}

	private void download(File file, HttpServletResponse response, String encoding, String contentType) {
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);
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
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
