package com.yihuacomputer.fish.api.fault;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

/**
 * 模板设置参数集合
 * @author YiHua
 *
 */
public interface INotifyMouldSet {

	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public String getFaultClassify();

	public void setFaultClassify(String faultClassify);

	public RunStatus getAppStatus();

	public void setAppStatus(RunStatus appStatus);

	public DeviceMod getFaultMod();

	public void setFaultMod(DeviceMod faultMod);
	
	public String getHwCode();

	public void setHwCode(String hwCode);

	public String getFaultDesc();

	public void setFaultDesc(String faultDesc);
}
