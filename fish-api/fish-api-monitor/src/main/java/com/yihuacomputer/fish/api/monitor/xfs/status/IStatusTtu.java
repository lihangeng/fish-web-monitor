package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * TTU文本终端状态接口
 * @author YiHua
 *
 */
public interface IStatusTtu {
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
	 * @param ttu
	 */
	public void setStatus(DeviceStatus ttu);

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
