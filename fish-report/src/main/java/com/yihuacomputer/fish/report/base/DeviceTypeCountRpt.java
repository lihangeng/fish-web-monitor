package com.yihuacomputer.fish.report.base;

import com.yihuacomputer.fish.api.report.base.IDeviceTypeCountRpt;

public class DeviceTypeCountRpt implements IDeviceTypeCountRpt {

	private String orgName;

	private String vendorName;

	private String devTypeName;

	private int deviceCount;

	private String countName;

	private String orgNameColumn;

	private String vendorNameColumn;

	private String devTypeNameColumn;

	private String totalColumn;

	private String subtotalColumn;

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

	public String getTotalColumn() {
		return totalColumn;
	}

	public void setTotalColumn(String totalColumn) {
		this.totalColumn = totalColumn;
	}

	public String getSubtotalColumn() {
		return subtotalColumn;
	}

	public void setSubtotalColumn(String subtotalColumn) {
		this.subtotalColumn = subtotalColumn;
	}

	

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public String getVendorName() {
		return vendorName;
	}

	@Override
	public String getDevTypeName() {
		return devTypeName;
	}

	@Override
	public int getDeviceCount() {
		return deviceCount;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	@Override
	public String getCountName() {
		return countName;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

}
