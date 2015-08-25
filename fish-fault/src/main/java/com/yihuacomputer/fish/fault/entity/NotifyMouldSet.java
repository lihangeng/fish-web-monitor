package com.yihuacomputer.fish.fault.entity;

import com.yihuacomputer.fish.api.fault.INotifyMouldSet;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

public class NotifyMouldSet implements INotifyMouldSet {

	private String terminalId;
	
	private String faultClassify;
	
	private RunStatus appStatus;
	
	private DeviceMod faultMod;
	
	private String hwCode;
	
	private String faultDesc;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getFaultClassify() {
		return faultClassify;
	}

	public void setFaultClassify(String faultClassify) {
		this.faultClassify = faultClassify;
	}

	public RunStatus getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(RunStatus appStatus) {
		this.appStatus = appStatus;
	}

	public DeviceMod getFaultMod() {
		return faultMod;
	}

	public void setFaultMod(DeviceMod faultMod) {
		this.faultMod = faultMod;
	}

	public String getHwCode() {
		return hwCode;
	}

	public void setHwCode(String hwCode) {
		this.hwCode = hwCode;
	}

	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}	
}
