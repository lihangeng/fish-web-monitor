package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 钞箱状态条件
 * @author YiHua
 *
 */
public interface IBoxStatusFilter {
	public boolean isAll();

	public boolean isFull();

	public boolean isLow();

	public boolean isEmpty();

	public boolean isUnknown();

	public boolean isHigh();

	public boolean isFatal();

	public boolean isHealthy();
	
	public void setHealthy(boolean healthy);
	
	public void setAll(boolean all);

	public void setFull(boolean full);

	public void setLow(boolean low);

	public void setEmpty(boolean empty);

	public void setUnknown(boolean unknown);

	public void setHigh(boolean high);

	public void setFatal(boolean faltal);

}
