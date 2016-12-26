package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * CPU信息
 * @author YiHua
 *
 */
public interface ICpu {
	
	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * @return
	 */
	public int getFrequency();

	/**
	 * @param frequency
	 */
	public void setFrequency(int frequency);

	/**
	 * @return
	 */
	public String getVendor();

	/**
	 * @param vendor
	 */
	public void setVendor(String vendor);

	/**
	 * @return
	 */
	public String getModel();

	/**
	 * @param model
	 */
	public void setModel(String model);

	/**
	 * @return
	 */
	public long getCacheSize();

	/**
	 * @param cacheSize
	 */
	public void setCacheSize(long cacheSize);

	/**
	 * @return
	 */
	public int getTotalCores();

	/**
	 * @param totalCores
	 */
	public void setTotalCores(int totalCores);

	/**
	 * @return
	 */
	public String getUser();

	/**
	 * @param user
	 */
	public void setUser(String user);

	/**
	 * @return
	 */
	public String getSys();

	/**
	 * @param sys
	 */
	public void setSys(String sys);

	/**
	 * @return
	 */
	public String getIdle();

	/**
	 * @param idle
	 */
	public void setIdle(String idle);

	/**
	 * @return
	 */
	public String getCombined();

	/**
	 * @param combined
	 */
	public void setCombined(String combined);

}
