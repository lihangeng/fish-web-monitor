package com.yihuacomputer.fish.api.report.base;

public interface IEveryMonthFaultCount {

	/**
	 * ID
	 * 
	 * @return
	 */
	public long getId();

	public void setId(long id);

	/**
	 * 故障模块
	 * 
	 * @return
	 */
	public String getDevMod();

	public void setDevMod(String devMod);

	/**
	 * 故障分类
	 * 
	 * @return
	 */
	public String getClassifyId();

	public void setClassifyId(String classifyId);

	/**
	 * 故障日期
	 * 
	 * @return
	 */
	public long getFaultDate();

	public void setFaultDate(long faultDate);

	/**
	 * 故障次数
	 * 
	 * @return
	 */
	public long getFaultCount();

	public void setFaultCount(long faultCount);
	
	/**
	 * 设备品牌
	 * @return
	 */
	public String getVendorName();
	/**
	 * 设备品牌
	 * @return
	 */
	public void setVendorName(String vendorName);
	/**
	 * 设备型号
	 * @return
	 */
	public String getDevType();
	/**
	 * 设备型号
	 * @return
	 */
	public void setDevType(String devType);
	
	/**
	 * 品牌ID
	 * @return
	 */
	public long getVendorId() ;
	/**
	 * 品牌ID
	 * @param vendorId
	 */
	public void setVendorId(long vendorId);

	/**
	 * 型号ID
	 * @return
	 */
	public long getDevTypeId();
	/**
	 * 型号ID
	 * @param devTypeId
	 */
	public void setDevTypeId(long devTypeId);

}
