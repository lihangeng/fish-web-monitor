package com.yihuacomputer.fish.api.monitor.software;

/**
 * 操作系统信息
 * @author YiHua
 *
 */
public interface IOS {
	/**
	 * @return
	 */
	public String getArch();

	/**
	 * @return
	 */
	public String getDescription();

	/**
	 * @return
	 */
	public String getType();

	/**
	 * @return
	 */
	public String getPatchLevel();

	/**
	 * @return
	 */
	public String getVendor();

	/**
	 * @return
	 */
	public String getVendorName();

	/**
	 * @return
	 */
	public String getVersion();

	/**
	 * @return
	 */
	public String getOsPath();

	/**
	 * @param arch
	 */
	public void setArch(String arch);

	/**
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * @param type
	 */
	public void setType(String type);

	/**
	 * @param patchLevel
	 */
	public void setPatchLevel(String patchLevel);

	/**
	 * @param vendor
	 */
	public void setVendor(String vendor);

	/**
	 * @param vendorName
	 */
	public void setVendorName(String vendorName);

	/**
	 * @param version
	 */
	public void setVersion(String version);

	/**
	 * @param osPatch
	 */
	public void setOsPath(String osPatch);
}
