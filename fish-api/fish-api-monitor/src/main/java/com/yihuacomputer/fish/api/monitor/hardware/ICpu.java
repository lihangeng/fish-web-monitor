package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * CPU信息
 * @author YiHua
 *
 */
public interface ICpu {
	
	public long getId();
	
	public int getFrequency();

	public void setFrequency(int frequency);

	public String getVendor();

	public void setVendor(String vendor);

	public String getModel();

	public void setModel(String model);

	public long getCacheSize();

	public void setCacheSize(long cacheSize);

	public int getTotalCores();

	public void setTotalCores(int totalCores);

	public String getUser();

	public void setUser(String user);

	public String getSys();

	public void setSys(String sys);

	public String getIdle();

	public void setIdle(String idle);

	public String getCombined();

	public void setCombined(String combined);

}
