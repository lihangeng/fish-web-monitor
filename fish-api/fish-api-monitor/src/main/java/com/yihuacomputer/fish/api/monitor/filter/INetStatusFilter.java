package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 网络状态监控条件
 * @author YiHua
 *
 */
public interface INetStatusFilter {
	/**
	 * @return
	 */
	public boolean isAll();

	/**
	 * @return
	 */
	public boolean isHealth();

	/**
	 * @return
	 */
	public boolean isWarning();

	/**
	 * @return
	 */
	public boolean isFatal();

	/**
	 * @return
	 */
	public boolean isUnknown();

	/**
	 * @param all
	 */
	public void setAll(boolean all);

	/**
	 * @param health
	 */
	public void setHealth(boolean health);

	/**
	 * @param warning
	 */
	public void setWarning(boolean warning);

	/**
	 * @param fatal
	 */
	public void setFatal(boolean fatal);

	/**
	 * @param unknown
	 */
	public void setUnknown(boolean unknown);

}
