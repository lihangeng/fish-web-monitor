package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 运行装条件
 * 
 * @author YiHua
 * 
 */
public interface IRunStatusFilter {
	public boolean isAll();

	public void setAll(boolean all);

	public boolean isHealth();

	public void setHealth(boolean health);

	public boolean isStop();

	public void setStop(boolean stop);

	public boolean isHalf();

	public void setHalf(boolean half);

	public boolean isUnknow();

	public void setUnknow(boolean unknow);

	public boolean isAtmpStop();

	public void setAtmpStop(boolean atmpStop);

	public boolean isMaintain();

	public void setMaintain(boolean maintain);

	public boolean isShutdown();

	public void setShutdown(boolean shutdown);

	public boolean isStopManMade();

	public void setStopManMade(boolean stopManMade);

	public boolean isVdm();

	public void setVdm(boolean vdm);

	public boolean isInitial();

	public void setInitial(boolean initial);

	public boolean isCustomer();

	public void setCustomer(boolean customer);

	public boolean isStopMod();

	public void setStopMod(boolean stopMod);

	public boolean isStopUnCashIn();

	public void setStopUnCashIn(boolean stopUnCashIn);

	public boolean isReBoot();

	public void setReBoot(boolean reBoot);

}
