//package com.yihuacomputer.common.util.export;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
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
//import org.apache.poi.hssf.util.Region;
//import org.apache.poi.ss.usermodel.Cell;
//
//import com.yihuacomputer.common.FishCfg;
//import com.yihuacomputer.common.util.DateUtils;
//import com.yihuacomputer.fish.api.person.IOrganization;
//import com.yihuacomputer.fish.api.person.IPerson;
//
//public class ExportOrg {
//	@SuppressWarnings("deprecation")
//	public void exportOrg(Map<IOrganization, List<IPerson>> map, HttpServletResponse response) {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("机构列表");
//		HSSFRow row = sheet.createRow(0);
//		sheet.setColumnWidth(0, 3600);
//		sheet.setColumnWidth(1, 7200);
//		sheet.setColumnWidth(2, 3600);
//		sheet.setColumnWidth(3, 7200);
//		sheet.setColumnWidth(4, 3600);
//		sheet.setColumnWidth(5, 7200);
//		sheet.setColumnWidth(6, 5000);
//		sheet.setColumnWidth(7, 5000);
//		// 创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("机构编号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("机构名称");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("机构级别");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("机构地址");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("机构邮编");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("描述");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("机构管理员");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("机构管理员手机");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//		cellStyle.setDataFormat(format.getFormat("@"));
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		List<OrgExportUtil> list = new ArrayList<OrgExportUtil>();
//		List<Region> listRegion = new ArrayList<Region>();
//		int start = 0;
//		int end = 0;
//		for (IOrganization o : map.keySet()) {
//			start++;
//			if (map.get(o) != null && map.get(o).size() != 0) {
//				end = start + map.get(o).size() - 1;
//				for (int i = 0; i <= 5; i++) {
//					listRegion.add(getRegion(start, end, i));
//				}
//				start = end;
//				for (IPerson p : map.get(o)) {
//					OrgExportUtil orgExportUtil = this.getOrgExportUtil(o);
//					orgExportUtil.setUserName(p.getName());
//					orgExportUtil.setUserMobile(p.getMobile());
//					list.add(orgExportUtil);
//				}
//			} else {
//				OrgExportUtil orgExportUtil = this.getOrgExportUtil(o);
//				list.add(orgExportUtil);
//			}
//		}
//		for (int i = 0; i < list.size(); i++) {
//			OrgExportUtil o = list.get(i);
//			row = sheet.createRow(i + 1);
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getCode()));
//
//			cell = row.createCell(1);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getName()));
//
//			cell = row.createCell(2);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getOrganizationLevel()));
//
//			cell = row.createCell(3);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getAddress()));
//
//			cell = row.createCell(4);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getZip()));
//
//			cell = row.createCell(5);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDescription()));
//
//			cell = row.createCell(6);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getUserName()));
//
//			cell = row.createCell(7);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getUserMobile()));
//		}
//		for (Region r : listRegion) {
//			sheet.addMergedRegion(r);
//		}
//		String date = DateUtils.getDate(new Date());
//		String name = "org_" + date + ".xls";
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
//	private OrgExportUtil getOrgExportUtil(IOrganization o) {
//		OrgExportUtil orgExportUtil = new OrgExportUtil();
//		orgExportUtil.setCode(o.getCode());
//		orgExportUtil.setAddress(o.getAddress());
//		orgExportUtil.setDescription(o.getDescription());
//		orgExportUtil.setName(o.getName());
//		orgExportUtil.setOrganizationLevel(o.getOrganizationLevel() == null ? "" : o.getOrganizationLevel().getText());
//		orgExportUtil.setZip(o.getZip());
//		return orgExportUtil;
//	}
//
//	private Region getRegion(int start, int end, int cell) {
//		return new Region(start, (short) cell, end, (short) cell);
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
