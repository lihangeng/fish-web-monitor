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
//import com.yihuacomputer.fish.api.device.IDevice;
//import com.yihuacomputer.fish.api.person.IPerson;
//
//public class ExportPerson {
//	@SuppressWarnings("deprecation")
//	public void exportPerson(Map<IPerson, List<IDevice>> map, HttpServletResponse response) {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("机构列表");
//		HSSFRow row = sheet.createRow(0);
//		sheet.setColumnWidth(0, 3000);
//		sheet.setColumnWidth(1, 3600);
//		sheet.setColumnWidth(2, 6000);
//		sheet.setColumnWidth(3, 3000);
//		sheet.setColumnWidth(4, 3600);
//		sheet.setColumnWidth(5, 4200);
//		sheet.setColumnWidth(6, 4200);
//		sheet.setColumnWidth(7, 6000);
//		sheet.setColumnWidth(8, 3600);
//		sheet.setColumnWidth(9, 3600);
//		sheet.setColumnWidth(10, 6000);
//		sheet.setColumnWidth(11, 6000);
//		sheet.setColumnWidth(12, 3600);
//		sheet.setColumnWidth(13, 4200);
//		sheet.setColumnWidth(14, 3600);
//		// 创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("工号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("姓名");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("状态");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("手机");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("邮箱");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("固话");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("备注");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(8);
//		cell.setCellValue("设备号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(9);
//		cell.setCellValue("设备IP");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(10);
//		cell.setCellValue("设备机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(11);
//		cell.setCellValue("机构备注");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(12);
//		cell.setCellValue("设备类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(13);
//		cell.setCellValue("设备分类");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(14);
//		cell.setCellValue("设备状态");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//		cellStyle.setDataFormat(format.getFormat("@"));
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		List<PersonExportUtil> list = new ArrayList<PersonExportUtil>();
//		List<Region> listRegion = new ArrayList<Region>();
//		int start = 0;
//		int end = 0;
//		for (IPerson o : map.keySet()) {
//			start++;
//			if (map.get(o) != null && map.get(o).size() != 0) {
//				end = start + map.get(o).size() - 1;
//				for (int i = 0; i <= 7; i++) {
//					listRegion.add(getRegion(start, end, i));
//				}
//				start = end;
//				for (IDevice p : map.get(o)) {
//					PersonExportUtil exportUtil = this.getPersonExportUtil(o);
//					exportUtil.setDevice(p.getDevType().getName());
//					exportUtil.setDeviceOrg(p.getOrganization().getName() + "(" + p.getOrganization().getCode() + ")");
//					exportUtil.setDeviceRemark(p.getDeviceExtend().getCarrier());
//					exportUtil.setDeviceState(p.getStatus().getText());
//					exportUtil.setDeviceType(p.getDevType().getDevCatalog().getName());
//					exportUtil.setIp(p.getIp().toString());
//					exportUtil.setTermId(p.getTerminalId());
//					list.add(exportUtil);
//				}
//			} else {
//				PersonExportUtil exportUtil = this.getPersonExportUtil(o);
//				list.add(exportUtil);
//			}
//		}
//		for (int i = 0; i < list.size(); i++) {
//			PersonExportUtil o = list.get(i);
//			row = sheet.createRow(i + 1);
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getJobNum()));
//
//			cell = row.createCell(1);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getName()));
//
//			cell = row.createCell(2);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getUserOrg()));
//
//			cell = row.createCell(3);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getState()));
//
//			cell = row.createCell(4);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getPhone()));
//
//			cell = row.createCell(5);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getEmail()));
//
//			cell = row.createCell(6);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getMobile()));
//
//			cell = row.createCell(7);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getRemark()));
//
//			cell = row.createCell(8);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getTermId()));
//
//			cell = row.createCell(9);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getIp()));
//
//			cell = row.createCell(10);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDeviceOrg()));
//
//			cell = row.createCell(11);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDeviceRemark()));
//
//			cell = row.createCell(12);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDevice()));
//
//			cell = row.createCell(13);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDeviceType()));
//
//			cell = row.createCell(14);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(o.getDeviceState()));
//
//		}
//		for (Region r : listRegion) {
//			sheet.addMergedRegion(r);
//		}
//		String date = DateUtils.getDate(new Date());
//		String name = "person_" + date + ".xls";
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
//	private PersonExportUtil getPersonExportUtil(IPerson o) {
//		PersonExportUtil personExportUtil = new PersonExportUtil();
//		personExportUtil.setCode(o.getCode());
//		personExportUtil.setEmail(o.getEmail());
//		personExportUtil.setJobNum(o.getJobNum());
//		personExportUtil.setMobile(o.getMobile());
//		personExportUtil.setName(o.getName());
//		personExportUtil.setPhone(o.getPhone());
//		personExportUtil.setRemark(o.getRemark());
//		personExportUtil.setState(o.getState() == null ? "" : o.getState().getText());
//		personExportUtil.setUserOrg(o.getOrganization().getName() + "(" + o.getOrganization().getCode() + ")");
//		return personExportUtil;
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
