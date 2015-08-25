package com.yihuacomputer.fish.api.report.base;

/**
 * 设备基本信息明细报表模型
 * @author YiHua
 *
 */
public interface IDeviceRpt {

	/**
	 * 终端号
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * 设备IP
	 * @return
	 */
	public String getDeviceIp();
	
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
	 * 安装类型（穿墙、大堂）
	 * @return
	 */
	public String getSetupType();
	
	/**
	 * 维护商
	 * @return
	 */
	public String getMantainOrg();
	
	/**
	 * 设备安装地址
	 * @return
	 */
	public String getAddress();
	
	/**
	 * 序列
	 * @return
	 */
	public String getIndex();

	/**
	 * 机构编号
	 * @return
	 */
	public String getOrgCode();

	/**
	 * 品牌名称
	 * @return
	 */
	public String getDevVendorName();

	/**
	 * 在行离行标志
	 * @return
	 */
	public String getAwayFlag();
	
}
