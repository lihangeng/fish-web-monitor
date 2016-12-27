package com.yihuacomputer.fish.api.report.fault;

/**
 * @author YiHua
 *
 */
public interface IEveryMonthFaultCount {

	/**
	 * ID
	 * 
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 故障模块
	 * 
	 * @return
	 */
	public String getDevMod();

	/**
	 * @param devMod
	 */
	public void setDevMod(String devMod);

	/**
	 * 故障分类
	 * 
	 * @return
	 */
	public String getClassifyId();

	/**
	 * @param classifyId
	 */
	public void setClassifyId(String classifyId);

	/**
	 * 故障日期
	 * 
	 * @return
	 */
	public long getFaultDate();

	/**
	 * @param faultDate
	 */
	public void setFaultDate(long faultDate);

	/**
	 * 故障次数
	 * 
	 * @return
	 */
	public long getFaultCount();

	/**
	 * @param faultCount
	 */
	public void setFaultCount(long faultCount);
	
	/**
	 * 设备品牌
	 * @return
	 */
	public String getVendorName();
	/**
	 * 设备品牌
	 * @param vendorName
	 */
	public void setVendorName(String vendorName);
	/**
	 * 设备型号
	 * @return
	 */
	public String getDevType();
	/**
	 * 设备型号
	 * @param devType
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
