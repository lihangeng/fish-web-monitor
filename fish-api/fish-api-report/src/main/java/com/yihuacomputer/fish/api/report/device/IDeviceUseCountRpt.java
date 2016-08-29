package com.yihuacomputer.fish.api.report.device;

/**
 * 设备运行情况统计
 * @author YiHua
 *
 */
public interface IDeviceUseCountRpt {

	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 设备类型
	 * @return
	 */
	public String getDevTypeName();
	
	/**
	 * 设备使用情况
	 * @return
	 */
	public String getDevUseState();
	
	/**
	 * 设备数量统计
	 * @return
	 */
	public int getDeviceCount();
	
	/**
	 * 统计名称
	 * @return
	 */
	public String getCountName();
}
