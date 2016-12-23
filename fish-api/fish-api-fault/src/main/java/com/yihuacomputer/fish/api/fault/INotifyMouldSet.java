package com.yihuacomputer.fish.api.fault;

import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

/**
 * 模板设置参数集合
 * @author YiHua
 *
 */
public interface INotifyMouldSet {

	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public String getFaultClassify();

	/**
	 * @param faultClassify
	 */
	public void setFaultClassify(String faultClassify);

	/**
	 * @return
	 */
	public RunStatus getAppStatus();

	/**
	 * @param appStatus
	 */
	public void setAppStatus(RunStatus appStatus);

	/**
	 * @return
	 */
	public DeviceMod getFaultMod();

	/**
	 * @param faultMod
	 */
	public void setFaultMod(DeviceMod faultMod);
	
	/**
	 * @return
	 */
	public String getHwCode();

	/**
	 * @param hwCode
	 */
	public void setHwCode(String hwCode);

	/**
	 * @return
	 */
	public String getFaultDesc();

	/**
	 * @param faultDesc
	 */
	public void setFaultDesc(String faultDesc);
}
