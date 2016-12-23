package com.yihuacomputer.fish.api.monitor.business;

import com.yihuacomputer.fish.api.device.RegStatus;

/**
 * 设备注册信息
 * @author YiHua
 *
 */
public interface IDeviceRegister {

	/**
	 * 获取设备注册Serial Number
	 * @return
	 */
	public String getRegisterSerialNumber();

	/**
	 * 设置签到次数
	 * @param signTimes
	 */
	public void setSignTimes(int signTimes);
	
	/**
	 * 获取签到次数
	 * @return
	 */
	public int getSignTimes();
	
	/**
	 * 设置注册次数
	 * @param regTimes
	 */
	public void setRegTimes(int regTimes);
	
	/**
	 * 获取注册次数
	 * @return
	 */
	public int getRegTimes();
	
	/**
	 * 获取ATMC APP版本号
	 * @return
	 */
	public String getAtmcVersion();
	
	/**
	 * 设置ATMC APP版本号
	 * @param atmcVersion
	 */
	public void setAtmcVersion(String atmcVersion);
	
	/**
	 * 获取注册成功标志
	 * @return
	 */
	public RegStatus getRegStatus();
	/**
	 * 设置注册成功标志
	 * @param regStatus
	 */
	public void setRegStatus(RegStatus regStatus);	

	/**
	 * 设置注册号
	 * @param sn
	 */
	public void setRegisterSerialNumber(String sn);
	
	/**
	 * 判断设备是否已经注册 
	 * @return
	 */
	public boolean isDeviceRegisted();
	
	/**
	 * 判断设备是否签到
	 * @return
	 */
	public boolean isDeivceSigned();
	
	/**
	 * 设置设备终端号
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	
	/**
	 * 获取设备号
	 * @return
	 */
	public String getTerminalId();
}
