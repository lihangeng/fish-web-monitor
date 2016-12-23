package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 模块状态条件
 * @author YiHua
 *
 */
public interface IModStatusFilter {
	
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
	 * @return
	 */
	public boolean isNodevice();
	
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
	
	/**
	 * @param nodevice
	 */
	public void setNodevice(boolean nodevice);
}
