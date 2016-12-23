package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 钞箱状态条件
 * @author YiHua
 *
 */
public interface IBoxStatusFilter {
	/**
	 * @return
	 */
	public boolean isAll();

	/**
	 * @return
	 */
	public boolean isFull();

	/**
	 * @return
	 */
	public boolean isLow();

	/**
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * @return
	 */
	public boolean isUnknown();

	/**
	 * @return
	 */
	public boolean isHigh();

	/**
	 * @return
	 */
	public boolean isFatal();

	/**
	 * @return
	 */
	public boolean isHealthy();
	
	/**
	 * @param healthy
	 */
	public void setHealthy(boolean healthy);
	
	/**
	 * @param all
	 */
	public void setAll(boolean all);

	/**
	 * @param full
	 */
	public void setFull(boolean full);

	/**
	 * @param low
	 */
	public void setLow(boolean low);

	/**
	 * @param empty
	 */
	public void setEmpty(boolean empty);

	/**
	 * @param unknown
	 */
	public void setUnknown(boolean unknown);

	/**
	 * @param high
	 */
	public void setHigh(boolean high);

	/**
	 * @param faltal
	 */
	public void setFatal(boolean faltal);

}
