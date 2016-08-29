package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRpt;

public class DeviceTypeCountRpt implements IDeviceTypeCountRpt {

	private String orgName;

	private String vendorName;

	private String devTypeName;

	private int deviceCount;

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


}
