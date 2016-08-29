package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRpt;

public class DeviceHardwareRpt implements IDeviceHardwareRpt {

	private String orgName;

	private String devTypeName;

	private String terminalId;

	private String memory;

	private String cpuHz;

	private String hardDisk;
	
	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public String getDevTypeName() {
		return devTypeName;
	}

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public String getMemory() {
		return memory;
	}

	@Override
	public String getCpuHz() {
		return cpuHz;
	}

	@Override
	public String getHardDisk() {
		return hardDisk;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public void setCpuHz(String cpuHz) {
		this.cpuHz = cpuHz;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}
}
