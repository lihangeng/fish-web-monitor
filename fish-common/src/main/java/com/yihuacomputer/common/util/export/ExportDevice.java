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
//import com.yihuacomputer.fish.api.device.NetType;
//import com.yihuacomputer.fish.api.person.IPerson;
//
//public class ExportDevice {
//	public void exportDevice(List<IDevice> data, HttpServletResponse response) {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("设备列表");
//		HSSFRow row = sheet.createRow(0);
//		// 设置表格的列宽
//		sheet.setColumnWidth(2, 4000);
//		sheet.setColumnWidth(5, 3000);
//		sheet.setColumnWidth(6, 3000);
//		sheet.setColumnWidth(9, 3500);
//		sheet.setColumnWidth(11, 5000);
//		sheet.setColumnWidth(13, 5000);
//		sheet.setColumnWidth(14, 5000);
//		sheet.setColumnWidth(16, 3000);
//		sheet.setColumnWidth(19, 3000);
//		sheet.setColumnWidth(20, 3000);
//		sheet.setColumnWidth(21, 3000);
//		sheet.setColumnWidth(22, 3000);
//		sheet.setColumnWidth(23, 3000);
//		sheet.setColumnWidth(24, 3000);
//		sheet.setColumnWidth(25, 3000);
//		sheet.setColumnWidth(26, 3000);
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("序号");
//
//		cell = row.createCell(1);
//		cell.setCellValue("设备号");
//
//		cell = row.createCell(2);
//		cell.setCellValue("设备IP地址");
//
//		cell = row.createCell(3);
//		cell.setCellValue("设备状态");
//
//		cell = row.createCell(4);
//		cell.setCellValue("设备维护商");
//
//		cell = row.createCell(5);
//		cell.setCellValue("所属机构");
//
//		cell = row.createCell(6);
//		cell.setCellValue("机构备注");
//
//		cell = row.createCell(7);
//		cell.setCellValue("设备型号");
//
//		cell = row.createCell(8);
//		cell.setCellValue("非现金标志");
//
//		cell = row.createCell(9);
//		cell.setCellValue("安装方式");
//
//		cell = row.createCell(10);
//		cell.setCellValue("在行离行标志");
//
//		cell = row.createCell(11);
//		cell.setCellValue("经营方式");
//
//		cell = row.createCell(12);
//		cell.setCellValue("报警金额(张)");
//
//		cell = row.createCell(13);
//		cell.setCellValue("设备地址");
//
//		cell = row.createCell(14);
//		cell.setCellValue("设备序列号");
//
//		cell = row.createCell(15);
//		cell.setCellValue("加钞机构");
//
//		cell = row.createCell(16);
//		cell.setCellValue("资金成本利率");
//
//		cell = row.createCell(17);
//		cell.setCellValue("atmc软件");
//
//		cell = row.createCell(18);
//		cell.setCellValue("厂商sp类型");
//
//		cell = row.createCell(19);
//		cell.setCellValue("购买日期");
//
//		cell = row.createCell(20);
//		cell.setCellValue("安装日期");
//
//		cell = row.createCell(21);
//		cell.setCellValue("启用日期");
//
//		cell = row.createCell(22);
//		cell.setCellValue("停用日期");
//
//		cell = row.createCell(23);
//		cell.setCellValue("保修到期日期");
//
//		cell = row.createCell(24);
//		cell.setCellValue("上次巡检日期");
//
//		cell = row.createCell(25);
//		cell.setCellValue("巡检到期日期");
//
//		cell = row.createCell(26);
//		cell.setCellValue("入账成本(元)");
//
//		cell = row.createCell(27);
//		cell.setCellValue("折旧年限(年)");
//
//		cell = row.createCell(28);
//		cell.setCellValue("装修费用");
//
//		cell = row.createCell(29);
//		cell.setCellValue("装修摊销年限(年)");
//
//		cell = row.createCell(30);
//		cell.setCellValue("物业租赁费用(元/月)");
//
//		cell = row.createCell(31);
//		cell.setCellValue("物业管理费用(元/月)");
//
//		cell = row.createCell(32);
//		cell.setCellValue("通讯线路费用(元/月)");
//
//		cell = row.createCell(33);
//		cell.setCellValue("电费(元/月)");
//
//		cell = row.createCell(34);
//		cell.setCellValue("加钞维护费用(元/月)");
//
//		cell = row.createCell(35);
//		cell.setCellValue("设备关注程度");
//
//		cell = row.createCell(36);
//		cell.setCellValue("网络类型");
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//
//		cellStyle.setDataFormat(format.getFormat("@"));
//
//		int count = 1;
//		for (IDevice device : data) {
//
//			row = sheet.createRow(count);
//
//			cell = row.createCell(0);
//			cell.setCellValue(count++);
//
//			cell = row.createCell(1);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getTerminalId()));
//
//			cell = row.createCell(2);
//			cell.setCellValue(cellValue(device.getIp() == null ? "" : device.getIp().toString()));
//
//			cell = row.createCell(3);
//			cell.setCellValue(cellValue(device.getStatus() == null ? "" : device.getStatus().getText()));
//
//			cell = row.createCell(4);
//			cell.setCellValue(cellValue(device.getDevService() == null ? "" : device.getDevService().getName()));
//
//			cell = row.createCell(5);
//			cell.setCellValue(cellValue(device.getOrganization() == null ? "" : device.getOrganization().getName()));
//
//			cell = row.createCell(7);
//			cell.setCellValue(cellValue(device.getDevType() == null ? "" : device.getDevType().getName()));
//
//			cell = row.createCell(8);
//			cell.setCellValue(cellValue(device.getCashType() == null ? "" : device.getCashType().getText()));
//
//			cell = row.createCell(9);
//			cell.setCellValue(cellValue(device.getSetupType() == null ? "" : device.getSetupType().getText()));
//
//			cell = row.createCell(10);
//			cell.setCellValue(cellValue(device.getAwayFlag() == null ? "" : device.getAwayFlag().getText()));
//
//			cell = row.createCell(11);
//			cell.setCellValue(cellValue(device.getWorkType() == null ? "" : device.getWorkType().getText()));
//
//			cell = row.createCell(12);
//			cell.setCellValue(cellValue(device.getCashboxLimit()));
//
//			cell = row.createCell(13);
//			cell.setCellValue(cellValue(device.getAddress()));
//
//			cell = row.createCell(17);
//			cell.setCellValue(cellValue(device.getAtmcSoft()));
//
//			cell = row.createCell(18);
//			cell.setCellValue(cellValue(device.getSp()));
//
//			if (device.getDeviceExtend() != null) {
//
//				cell = row.createCell(13);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getSerial()));
//
//				cell = row.createCell(6);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getCarrier()));
//
//				cell = row.createCell(14);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getMoneyOrg()));
//
//				cell = row.createCell(16);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getCostInterest()));
//
//				cell = row.createCell(19);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getBuyDate()));
//
//				cell = row.createCell(20);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getInstallDate()));
//
//				cell = row.createCell(21);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getStartDate()));
//
//				cell = row.createCell(22);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getStopDate()));
//
//				cell = row.createCell(23);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getExpireDate()));
//
//				cell = row.createCell(24);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getLastPmDate()));
//
//				cell = row.createCell(25);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getExpirePmDate()));
//
//				cell = row.createCell(26);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getPrice()));
//
//				cell = row.createCell(27);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getDepreciationLife()));
//
//				cell = row.createCell(28);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getDecoration()));
//
//				cell = row.createCell(29);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getDecorationCost()));
//
//				cell = row.createCell(30);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getGovernanceRent()));
//
//				cell = row.createCell(31);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getGovernanceCost()));
//
//				cell = row.createCell(32);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getNetCost()));
//
//				cell = row.createCell(33);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getPowerCost()));
//
//				cell = row.createCell(34);
//				cell.setCellValue(cellValue(device.getDeviceExtend().getMoneyCost()));
//				NetType netType = device.getDeviceExtend().getNetType();
//				cell = row.createCell(36);
//				cell.setCellValue(cellValue(netType == null ? "" : netType.getText()));
//
//			}
//			cell = row.createCell(35);
//			cell.setCellValue(cellValue(device.getCareLevel() == null ? "" : device.getCareLevel().getText()));
//		}
//		String date = DateUtils.getDate(new Date());
//		String name = "device_" + date + ".xls";
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
//	@SuppressWarnings("deprecation")
//	public void exportDevice(List<IDevice> data, Map<IDevice, String> planMap, Map<IDevice, List<IPerson>> personMap, HttpServletResponse response) {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("设备列表");
//		HSSFRow row = sheet.createRow(0);
//		// 设置表格的列宽
//		sheet.setColumnWidth(0, 3600);
//		sheet.setColumnWidth(1, 4200);
//		sheet.setColumnWidth(2, 3600);
//		sheet.setColumnWidth(3, 3600);
//		sheet.setColumnWidth(4, 6600);
//		sheet.setColumnWidth(5, 4200);
//		sheet.setColumnWidth(7, 3000);
//		sheet.setColumnWidth(6, 3000);
//		sheet.setColumnWidth(9, 4200);
//		sheet.setColumnWidth(10, 3000);
//		sheet.setColumnWidth(11, 5400);
//		sheet.setColumnWidth(12, 5400);
//		sheet.setColumnWidth(13, 4200);
//		sheet.setColumnWidth(14, 4200);
//		sheet.setColumnWidth(15, 4200);
//		sheet.setColumnWidth(16, 7200);
//		sheet.setColumnWidth(17, 3000);
//
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("设备号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue("设备IP地址");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(2);
//		cell.setCellValue("设备状态");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(3);
//		cell.setCellValue("设备维护商");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(4);
//		cell.setCellValue("所属机构");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(5);
//		cell.setCellValue("设备型号");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(7);
//		cell.setCellValue("设备分类");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(6);
//		cell.setCellValue("非现金标志");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(8);
//		cell.setCellValue("安装方式");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(9);
//		cell.setCellValue("在行离行标志");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(10);
//		cell.setCellValue("经营方式");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(11);
//		cell.setCellValue("开机方案");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(12);
//		cell.setCellValue("备注");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(13);
//		cell.setCellValue("相关人员类型");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(14);
//		cell.setCellValue("相关人员姓名");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(15);
//		cell.setCellValue("相关人员手机");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(16);
//		cell.setCellValue("设备地址");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(17);
//		cell.setCellValue("设备序列号");
//		cell.setCellStyle(style);
//
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		HSSFDataFormat format = wb.createDataFormat();
//
//		cellStyle.setDataFormat(format.getFormat("@"));
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		List<DeviceExportUtil> listDevice = new ArrayList<DeviceExportUtil>();
//		List<Region> listRegion = new ArrayList<Region>();
//		int start = 0;
//		int end = 0;
//		for (IDevice o : personMap.keySet()) {
//			start++;
//			if (personMap.get(o) != null && personMap.get(o).size() != 0) {
//				end = start + personMap.get(o).size() - 1;
//				for (int i = 0; i <= 12; i++) {
//					listRegion.add(getRegion(start, end, i));
//				}
//				    listRegion.add(getRegion(start,end, 16));
//				    listRegion.add(getRegion(start,end, 17));
//				start = end;
//				for (IPerson p : personMap.get(o)) {
//					DeviceExportUtil deviceExportUtil = this.getDeviceExportUtil(o, planMap);
//					deviceExportUtil.setPersonName(p.getName());
//					deviceExportUtil.setPersonMobile(p.getMobile().toString());
//					if (p.getType() == null) {
//						deviceExportUtil.setPersonType("机构管理员");
//					} else {
//						// 0管机员,1维护员
//						if (p.getType().getId() == 1) {
//							deviceExportUtil.setPersonType("维护员");
//						} else {
//							deviceExportUtil.setPersonType("管机员");
//						}
//					}
//					listDevice.add(deviceExportUtil);
//				}
//			} else {
//				DeviceExportUtil deviceExportUtil = this.getDeviceExportUtil(o, planMap);
//				listDevice.add(deviceExportUtil);
//			}
//		}
//		for (int i = 0; i < listDevice.size(); i++) {
//			DeviceExportUtil device = listDevice.get(i);
//			row = sheet.createRow(i + 1);
//
//			cell = row.createCell(0);
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getTerminalId()));
//
//			cell = row.createCell(1);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getIp()));
//
//			cell = row.createCell(2);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getStatus()));
//
//			cell = row.createCell(3);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getDevService()));
//
//			cell = row.createCell(4);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getOrganization()));
//
//			cell = row.createCell(5);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getDevType()));
//
//			cell = row.createCell(6);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getCashType()));
//
//			cell = row.createCell(7);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getDevice()));
//
//			cell = row.createCell(8);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getSetupType()));
//
//			cell = row.createCell(9);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getAwayFlag()));
//
//			cell = row.createCell(10);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getWorkType()));
//
//			cell = row.createCell(11);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getPlan()));
//
//			cell = row.createCell(12);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getCarrier()));
//
//			cell = row.createCell(13);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getPersonType()));
//
//			cell = row.createCell(14);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getPersonName()));
//
//			cell = row.createCell(15);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getPersonMobile()));
//
//			cell = row.createCell(16);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getAddress()));
//
//			cell = row.createCell(17);
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(cellValue(device.getSerial()));
//
//		}
//		for (Region r : listRegion) {
//			sheet.addMergedRegion(r);
//		}
//		String date = DateUtils.getDate(new Date());
//		String name = "device_" + date + ".xls";
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
//	private DeviceExportUtil getDeviceExportUtil(IDevice o, Map<IDevice, String> planMap) {
//		DeviceExportUtil deviceExportUtil = new DeviceExportUtil();
//		deviceExportUtil.setTerminalId(o.getTerminalId());
//		deviceExportUtil.setIp(o.getIp() == null ? "" : o.getIp().toString());
//		deviceExportUtil.setStatus(o.getStatus() == null ? "" : o.getStatus().getText());
//		deviceExportUtil.setDevService(o.getDevService() == null ? "" : o.getDevService().getName());
//		deviceExportUtil.setOrganization(o.getOrganization().getName() + "(" + o.getOrganization().getCode() + ")");
//		deviceExportUtil.setDevType(o.getDevType() == null ? "" : o.getDevType().getName());
//		deviceExportUtil.setCashType(o.getCashType() == null ? "" : o.getCashType().getText());
//		deviceExportUtil.setSetupType(o.getSetupType() == null ? "" : o.getSetupType().getText());
//		deviceExportUtil.setAwayFlag(o.getAwayFlag() == null ? "" : o.getAwayFlag().getText());
//		deviceExportUtil.setWorkType(o.getWorkType() == null ? "" : o.getWorkType().getText());
//		deviceExportUtil.setAddress(o.getAddress() == null ? "" : o.getAddress());
//
//		deviceExportUtil.setPlan(planMap.get(o));
//		deviceExportUtil.setAtmcSoft(o.getAtmcSoft());
//		deviceExportUtil.setSp(o.getSp());
//		deviceExportUtil.setDevice(o.getDevType().getDevCatalog().getName());
//		if (o.getDeviceExtend() != null) {
//			deviceExportUtil.setSerial(o.getDeviceExtend().getSerial());
//			deviceExportUtil.setCarrier(o.getDeviceExtend().getCarrier());
//		}
//		return deviceExportUtil;
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
