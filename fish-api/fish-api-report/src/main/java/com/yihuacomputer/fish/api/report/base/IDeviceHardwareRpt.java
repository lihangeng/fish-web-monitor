package com.yihuacomputer.fish.api.report.base;

/**
 * 设备硬件信息明细模型
 * @author YiHua
 *
 */
public interface IDeviceHardwareRpt {

	/**
	 * 机构信息
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 设备类型名称
	 * @return
	 */
	public String getDevTypeName();
	
	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * 内存信息
	 * @return
	 */
	public String getMemory();
	
	/**
	 * CPU信息
	 * @return
	 */
	public String getCpuHz();
	
	/**
	 * 硬盘信息
	 * @return
	 */
	public String getHardDisk();
}
