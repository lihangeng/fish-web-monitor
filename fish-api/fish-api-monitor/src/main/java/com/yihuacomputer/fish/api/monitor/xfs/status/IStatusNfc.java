package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * Nfd射频读卡器读卡器模块状态信息
 * @author Administrator
 *
 */
public interface IStatusNfc {
	
	/**
	 * 获取硬件主状态
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus();

	/**
	 * 获取状态代码
	 * @return 状态代码
	 */
	public String getCode();
	

	public void setStatus(DeviceStatus rfid);
	
	public void setCode(String code);
	
	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();
	
	public void setHwCode(String hwCode);

}
