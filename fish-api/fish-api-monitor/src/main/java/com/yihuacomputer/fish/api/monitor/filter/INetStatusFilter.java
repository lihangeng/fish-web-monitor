package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 网络状态监控条件
 * @author YiHua
 *
 */
public interface INetStatusFilter {
	public boolean isAll();

	public boolean isHealth();

	public boolean isWarning();

	public boolean isFatal();

	public boolean isUnknown();

	public void setAll(boolean all);

	public void setHealth(boolean health);

	public void setWarning(boolean warning);

	public void setFatal(boolean fatal);

	public void setUnknown(boolean unknown);

}
