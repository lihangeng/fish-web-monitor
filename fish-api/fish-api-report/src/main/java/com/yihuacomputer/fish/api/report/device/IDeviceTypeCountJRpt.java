package com.yihuacomputer.fish.api.report.device;

/**
 * @author YiHua
 *
 */
public interface IDeviceTypeCountJRpt {
	/**
	 * @return
	 */
	public String getCountName() ;
	/**
	 * @param countName
	 */
	public void setCountName(String countName);
	/**
	 * @return
	 */
	public String getOrgName();
	/**
	 * @param orgName
	 */
	public void setOrgName(String orgName);
	/**
	 * @return
	 */
	public String getVendorName();
	/**
	 * @param vendorName
	 */
	public void setVendorName(String vendorName);
	/**
	 * @return
	 */
	public String getDevTypeName();
	/**
	 * @param devTypeName
	 */
	public void setDevTypeName(String devTypeName);
	/**
	 * @return
	 */
	public int getDeviceCount();
	/**
	 * @param deviceCount
	 */
	public void setDeviceCount(int deviceCount);
	/**
	 * @return
	 */
	public String getOrgNameColumn();
	/**
	 * @param orgNameColumn
	 */
	public void setOrgNameColumn(String orgNameColumn);
	/**
	 * @return
	 */
	public String getVendorNameColumn() ;
	/**
	 * @param vendorNameColumn
	 */
	public void setVendorNameColumn(String vendorNameColumn);
	/**
	 * @return
	 */
	public String getDevTypeNameColumn();
	/**
	 * @param devTypeNameColumn
	 */
	public void setDevTypeNameColumn(String devTypeNameColumn);
	/**
	 * @return
	 */
	public String getSubtotalColumn();
	/**
	 * @param subtotalColumn
	 */
	public void setSubtotalColumn(String subtotalColumn);
	/**
	 * @return
	 */
	public String getTotalColumn();
	/**
	 * @param totalColumn
	 */
	public void setTotalColumn(String totalColumn);
	
}
