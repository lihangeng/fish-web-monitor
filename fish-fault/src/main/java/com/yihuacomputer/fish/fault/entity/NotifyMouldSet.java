package com.yihuacomputer.fish.fault.entity;

import com.yihuacomputer.fish.api.fault.INotifyMouldSet;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

/**
 * @author YiHua
 *
 */
public class NotifyMouldSet implements INotifyMouldSet {

	private String terminalId;
	
	private String faultClassify;
	
	private RunStatus appStatus;
	
	private DeviceMod faultMod;
	
	private String hwCode;
	
	private String faultDesc;

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String getFaultClassify() {
		return faultClassify;
	}

	@Override
	public void setFaultClassify(String faultClassify) {
		this.faultClassify = faultClassify;
	}

	@Override
	public RunStatus getAppStatus() {
		return appStatus;
	}

	@Override
	public void setAppStatus(RunStatus appStatus) {
		this.appStatus = appStatus;
	}

	@Override
	public DeviceMod getFaultMod() {
		return faultMod;
	}

	@Override
	public void setFaultMod(DeviceMod faultMod) {
		this.faultMod = faultMod;
	}

	@Override
	public String getHwCode() {
		return hwCode;
	}

	@Override
	public void setHwCode(String hwCode) {
		this.hwCode = hwCode;
	}

	@Override
	public String getFaultDesc() {
		return faultDesc;
	}

	@Override
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}	
}
