package com.yihuacomputer.fish.report.base;

import com.yihuacomputer.fish.api.report.base.IDeviceUseCountRpt;

public class DeviceUseCountRpt implements IDeviceUseCountRpt {

	private String orgName;

	private String devTypeName;

	private String DevUseState;

	private int deviceCount;

	private String countName;

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public String getDevTypeName() {
		return devTypeName;
	}

	@Override
	public String getDevUseState() {
		return DevUseState;
	}

	@Override
	public int getDeviceCount() {
		return deviceCount;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public void setDevUseState(String devUseState) {
		DevUseState = devUseState;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	@Override
	public String getCountName() {
		return countName;
	}

	public void setCouneName(String countName) {
		this.countName = countName;
	}

}
