package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * BIOS信息
 * @author YiHua
 *
 */
public interface IBios {
	public String getBiosVendor();

	public String getBiosVersion();

	public String getBiosReleaseDate();

	public void setBiosReleaseDate(String biosReleaseDate);

	public void setBiosVendor(String biosVendor);

	public void setBiosVersion(String biosVersion);
}
