package com.yihuacomputer.fish.api.monitor.filter;

/**
 * 运行装条件
 * 
 * @author YiHua
 * 
 */
public interface IRunStatusFilter {
	/**
	 * @return
	 */
	public boolean isAll();

	/**
	 * @param all
	 */
	public void setAll(boolean all);

	/**
	 * @return
	 */
	public boolean isHealth();

	/**
	 * @param health
	 */
	public void setHealth(boolean health);

	/**
	 * @return
	 */
	public boolean isStop();

	/**
	 * @param stop
	 */
	public void setStop(boolean stop);

	/**
	 * @return
	 */
	public boolean isHalf();

	/**
	 * @param half
	 */
	public void setHalf(boolean half);

	/**
	 * @return
	 */
	public boolean isUnknow();

	/**
	 * @param unknow
	 */
	public void setUnknow(boolean unknow);

	/**
	 * @return
	 */
	public boolean isAtmpStop();

	/**
	 * @param atmpStop
	 */
	public void setAtmpStop(boolean atmpStop);

	/**
	 * @return
	 */
	public boolean isMaintain();

	/**
	 * @param maintain
	 */
	public void setMaintain(boolean maintain);

	/**
	 * @return
	 */
	public boolean isShutdown();

	/**
	 * @param shutdown
	 */
	public void setShutdown(boolean shutdown);

	/**
	 * @return
	 */
	public boolean isStopManMade();

	/**
	 * @param stopManMade
	 */
	public void setStopManMade(boolean stopManMade);

	/**
	 * @return
	 */
	public boolean isVdm();

	/**
	 * @param vdm
	 */
	public void setVdm(boolean vdm);

	/**
	 * @return
	 */
	public boolean isInitial();

	/**
	 * @param initial
	 */
	public void setInitial(boolean initial);

	/**
	 * @return
	 */
	public boolean isCustomer();

	/**
	 * @param customer
	 */
	public void setCustomer(boolean customer);

	/**
	 * @return
	 */
	public boolean isStopMod();

	/**
	 * @param stopMod
	 */
	public void setStopMod(boolean stopMod);

	/**
	 * @return
	 */
	public boolean isStopUnCashIn();

	/**
	 * @param stopUnCashIn
	 */
	public void setStopUnCashIn(boolean stopUnCashIn);

	/**
	 * @return
	 */
	public boolean isReBoot();

	/**
	 * @param reBoot
	 */
	public void setReBoot(boolean reBoot);

}
