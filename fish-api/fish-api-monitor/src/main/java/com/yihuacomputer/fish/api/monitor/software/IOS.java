package com.yihuacomputer.fish.api.monitor.software;

/**
 * 操作系统信息
 * @author YiHua
 *
 */
public interface IOS {
	public String getArch();

	public String getDescription();

	public String getType();

	public String getPatchLevel();

	public String getVendor();

	public String getVendorName();

	public String getVersion();

	public String getOsPath();

	public void setArch(String arch);

	public void setDescription(String description);

	public void setType(String type);

	public void setPatchLevel(String patchLevel);

	public void setVendor(String vendor);

	public void setVendorName(String vendorName);

	public void setVersion(String version);

	public void setOsPath(String osPatch);
}
