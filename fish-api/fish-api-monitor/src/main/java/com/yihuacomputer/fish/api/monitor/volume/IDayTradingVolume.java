package com.yihuacomputer.fish.api.monitor.volume;

/**
 * 天交易额度
 * @author GQ
 *
 */
public interface IDayTradingVolume {

	public long getId();
	public void setId(long id);
	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();
	/**
	 * 设备号
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	/**
	 * 出钞箱金额
	 * @return
	 */
	public double getAmtOut();
	/**
	 * 出钞箱金额
	 * @param amtOut
	 */
	public void setAmtOut(double amtOut);
	/**
	 * 进钞箱金额
	 * @return
	 */
	public double getAmtIn();
	/**
	 * 进钞箱金额
	 * @param amtIn
	 */
	public void setAmtIn(double amtIn);
	/**
	 * 交易日期
	 * @return
	 */
	public int getTransDate();
	/**
	 * 交易日期
	 * @param transDate
	 */
	public void setTransDate(int transDate);
}
