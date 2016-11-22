package com.yihuacomputer.fish.api.report.device;

public interface IDeviceTypeCountJRpt {
	public String getCountName() ;
	public void setCountName(String countName);
	public String getOrgName();
	public void setOrgName(String orgName);
	public String getVendorName();
	public void setVendorName(String vendorName);
	public String getDevTypeName();
	public void setDevTypeName(String devTypeName);
	public int getDeviceCount();
	public void setDeviceCount(int deviceCount);
	public String getOrgNameColumn();
	public void setOrgNameColumn(String orgNameColumn);
	public String getVendorNameColumn() ;
	public void setVendorNameColumn(String vendorNameColumn);
	public String getDevTypeNameColumn();
	public void setDevTypeNameColumn(String devTypeNameColumn);
	public String getSubtotalColumn();
	public void setSubtotalColumn(String subtotalColumn);
	public String getTotalColumn();
	public void setTotalColumn(String totalColumn);
	
}
