package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 *ISC身份证扫描仪模块状态信息
 * @author wuqing
 *
 */
public interface IStatusIsc {

	/**
	 * 获取硬件主状态
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus();

	public void setStatus(DeviceStatus isc);

	/**
	 * 获取状态代码
	 * @return 状态代码
	 */
	public String getCode();

	public void setCode(String code);

	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();

	public void setHwCode(String hwCode);

}
