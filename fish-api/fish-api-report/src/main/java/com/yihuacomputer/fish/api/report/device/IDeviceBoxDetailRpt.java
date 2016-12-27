package com.yihuacomputer.fish.api.report.device;

/**
 * 设备钞箱明细模型
 * @author YiHua
 *
 */
public interface IDeviceBoxDetailRpt {

	/**
	 * 终端编号
	 * @return
	 */
	public String getDeviceNo();
	
	/**
	 * 所属机构
	 * @return
	 * */
	public String getOrgName();
	
	/**
	 * 终端品牌
	 * @return
	 */
	public String getVendorName();
	
	/**
	 * 终端类型
	 * @return
	 */
	public String getTypeName();
	
	/**
	 * 安装类型
	 * @return
	 */
	public String getSetupType();
	/**
	 * @param setupType
	 */
	public void setSetupType(String setupType);
	
	/**
	 * 终端状态
	 * @return
	 */
	public String getStatus();
	/**
	 * @param status
	 */
	public void setStatus(String status);
	
	/**
	 * 钞箱余额
	 * @return
	 */
	public String getBoxLeft();
}
