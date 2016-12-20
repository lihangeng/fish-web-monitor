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
	
	public void setId(long id);
	
	/**
	 * 厂商编号
	 * @return
	 */
	public long getVendor();
	
	public void setVendor(long vendor);
	
	/**
	 * 厂商故障码
	 * @return
	 */
	public String getCode();
	
	public void setCode(String code);
	
	/**
	 * 厂商故障描述
	 * @return
	 */
	public String getDescription();
	
	public void setDescription(String description);
	
	/**
	 * 解决方案
	 * @return
	 */
	public String getSolution();
	
	public void setSolution(String solution);
}
