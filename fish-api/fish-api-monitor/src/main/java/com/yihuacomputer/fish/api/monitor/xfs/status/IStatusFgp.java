package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 *FGP指纹仪模块状态信息
 * @author wuqing
 *
 */
public interface IStatusFgp {

	/**
	 * 获取硬件主状态
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus();

	/**
	 * @param fgp
	 */
	public void setStatus(DeviceStatus fgp);

	/**
	 * 获取状态代码
	 * @return 状态代码
	 */
	public String getCode();

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
