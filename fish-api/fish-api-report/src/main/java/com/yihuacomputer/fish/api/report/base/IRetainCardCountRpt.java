package com.yihuacomputer.fish.api.report.base;

/**
 * 吞卡统计报表
 * @author YiHua
 *
 */
public interface IRetainCardCountRpt {

	/**
	 * 终端号
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 吞卡统计数
	 * @return
	 */
	public long getRetainCount();
	
	/**
	 * 设备机型
	 * @return
	 */
	public String getDeviceType();
	
	/**
	 * 统计名称
	 * @return
	 */
	public String getCountName();
}
