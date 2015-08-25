package com.yihuacomputer.fish.report.base;

import com.yihuacomputer.fish.api.report.base.IRetainCardCountRpt;

public class RetainCardCountRpt implements IRetainCardCountRpt {

	private String terminalId;
	
	private String orgName;
	
	private String deviceType;
	
	private long  retainCount;
	
	private String countName;
	
	
	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public long getRetainCount() {
		return retainCount;
	}

	@Override
	public String getDeviceType() {
		return deviceType;
	}

	@Override
	public String getCountName() {
		return countName;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setRetainCount(long retainCount) {
		this.retainCount = retainCount;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}	
}
