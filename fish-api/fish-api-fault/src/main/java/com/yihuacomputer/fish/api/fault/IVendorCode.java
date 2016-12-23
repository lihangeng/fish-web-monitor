package com.yihuacomputer.fish.api.fault;

/**
 * 厂商故障码
 * @author Yihua
 *
 */
public interface IVendorCode {

	/**
	 * 编号
	 * @return
	 */
	public long getId();
	
	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 厂商编号
	 * @return
	 */
	public long getVendor();
	
	/**
	 * @param vendor
	 */
	public void setVendor(long vendor);
	
	/**
	 * 厂商故障码
	 * @return
	 */
	public String getCode();
	
	/**
	 * @param code
	 */
	public void setCode(String code);
	
	/**
	 * 厂商故障描述
	 * @return
	 */
	public String getDescription();
	
	/**
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * 解决方案
	 * @return
	 */
	public String getSolution();
	
	/**
	 * @param solution
	 */
	public void setSolution(String solution);
}
