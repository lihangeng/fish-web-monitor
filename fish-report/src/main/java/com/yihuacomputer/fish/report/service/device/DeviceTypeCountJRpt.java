package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountJRpt;


public class DeviceTypeCountJRpt implements IDeviceTypeCountJRpt{
	
	private String countName;
	private String orgName;
	private String vendorName;
	private String devTypeName;
	private int deviceCount;
	private String vendorNameColumn;
	private String orgNameColumn;
	private String devTypeNameColumn;
	private String subtotalColumn;
	private String totalColumn;
	
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		this.countName = countName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getDevTypeName() {
		return devTypeName;
	}
	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}
	public int getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}
	public String getOrgNameColumn() {
		return orgNameColumn;
	}
	public void setOrgNameColumn(String orgNameColumn) {
		this.orgNameColumn = orgNameColumn;
	}
	public String getVendorNameColumn() {
		return vendorNameColumn;
	}
	public void setVendorNameColumn(String vendorNameColumn) {
		this.vendorNameColumn = vendorNameColumn;
	}
	public String getDevTypeNameColumn() {
		return devTypeNameColumn;
	}
	public void setDevTypeNameColumn(String devTypeNameColumn) {
		this.devTypeNameColumn = devTypeNameColumn;
	}
	public String getSubtotalColumn() {
		return subtotalColumn;
	}
	public void setSubtotalColumn(String subtotalColumn) {
		this.subtotalColumn = subtotalColumn;
	}
	public String getTotalColumn() {
		return totalColumn;
	}
	public void setTotalColumn(String totalColumn) {
		this.totalColumn = totalColumn;
	}
	
}
