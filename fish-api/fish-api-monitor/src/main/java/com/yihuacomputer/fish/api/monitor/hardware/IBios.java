package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * BIOS信息
 * @author YiHua
 *
 */
public interface IBios {
	/**
	 * @return
	 */
	public String getBiosVendor();

	/**
	 * @return
	 */
	public String getBiosVersion();

	/**
	 * @return
	 */
	public String getBiosReleaseDate();

	/**
	 * @param biosReleaseDate
	 */
	public void setBiosReleaseDate(String biosReleaseDate);

	/**
	 * @param biosVendor
	 */
	public void setBiosVendor(String biosVendor);

	/**
	 * @param biosVersion
	 */
	public void setBiosVersion(String biosVersion);
}
