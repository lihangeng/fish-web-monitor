package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * @author GQ
 * 
 */
public interface IStatusUkd {
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
	 * 设置硬件主状态
	 *
	 * @param cdm
	 */
	public void setStatus(DeviceStatus bcr);

	/**
	 * 设置状态码
	 *
	 * @param code
	 */
	public void setCode(String code);

	public String getHwCode();

	public void setHwCode(String hwCode);

	@Override
	public int hashCode();

	@Override
	public boolean equals(Object obj);
}
