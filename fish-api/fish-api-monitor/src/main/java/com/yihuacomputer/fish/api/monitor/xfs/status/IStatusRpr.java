package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * RPR凭条打印模块信息
 * @author YiHua
 *
 */
public interface IStatusRpr {
	/**
	 * 获取硬件主状态
	 * 
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus();

	/**
	 * 获取状态代码
	 * 
	 * @return 状态代码
	 */
	public String getCode();

	/**
	 * @param rpr
	 */
	public void setStatus(DeviceStatus rpr);

	/**
	 * @param code
	 */
	public void setCode(String code);
	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();
	
	/**
	 * @param hwCode
	 */
	public void setHwCode(String hwCode);
}
