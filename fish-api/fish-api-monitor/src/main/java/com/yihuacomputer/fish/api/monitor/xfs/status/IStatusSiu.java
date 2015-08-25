package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * SIU传感器模块状态信息
 * @author YiHua
 *
 */
public interface IStatusSiu {
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

	public void setStatus(DeviceStatus siu);

	public void setCode(String code);
	
	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();
	
	public void setHwCode(String hwCode);
}
