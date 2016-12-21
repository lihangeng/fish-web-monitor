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
	
	@Override
	public String getCountName() {
		return countName;
	}
	
	@Override
	public void setCountName(String countName) {
		this.countName = countName;
	}
	
	@Override
	public String getOrgName() {
		return orgName;
	}
	
	@Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Override
	public String getVendorName() {
		return vendorName;
	}
	
	@Override
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Override
	public String getDevTypeName() {
		return devTypeName;
	}
	
	@Override
	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}
	
	@Override
	public int getDeviceCount() {
		return deviceCount;
	}
	
	@Override
	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}
	
	@Override
	public String getOrgNameColumn() {
		return orgNameColumn;
	}
	
	@Override
	public void setOrgNameColumn(String orgNameColumn) {
		this.orgNameColumn = orgNameColumn;
	}
	
	@Override
	public String getVendorNameColumn() {
		return vendorNameColumn;
	}
	
	@Override
	public void setVendorNameColumn(String vendorNameColumn) {
		this.vendorNameColumn = vendorNameColumn;
	}
	
	@Override
	public String getDevTypeNameColumn() {
		return devTypeNameColumn;
	}
	
	@Override
	public void setDevTypeNameColumn(String devTypeNameColumn) {
		this.devTypeNameColumn = devTypeNameColumn;
	}
	
	@Override
	public String getSubtotalColumn() {
		return subtotalColumn;
	}
	
	@Override
	public void setSubtotalColumn(String subtotalColumn) {
		this.subtotalColumn = subtotalColumn;
	}
	
	@Override
	public String getTotalColumn() {
		return totalColumn;
	}
	
	@Override
	public void setTotalColumn(String totalColumn) {
		this.totalColumn = totalColumn;
	}
	
}
