package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 模块状态条件
 * @author YiHua
 *
 */
public interface IModStatusFilter {
	
	public boolean isAll();

	public boolean isHealth();

	public boolean isWarning();

	public boolean isFatal();

	public boolean isUnknown();

	public boolean isNodevice();
	
	public void setAll(boolean all);

	public void setHealth(boolean health);

	public void setWarning(boolean warning);

	public void setFatal(boolean fatal);

	public void setUnknown(boolean unknown);
	
	public void setNodevice(boolean nodevice);
}
