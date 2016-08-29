package com.yihuacomputer.fish.api.report.device;

/**
 * 设备品牌统计报表模型
 * @author YiHua
 *
 */
public interface IDeviceTypeCountRpt {

	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 设备品牌
	 * @return
	 */
	public String getVendorName();
	
	/**
	 * 设备类型
	 * @return
	 */
	public String getDevTypeName();
	
	/**
	 * 设备数量
	 * @return
	 */
	public int getDeviceCount();
	
	
}
