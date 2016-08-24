package com.yihuacomputer.fish.api.monitor.volume;

/**
 * @author GQ
 * 当前月的日均交易信息
 */
public interface IMonthDailyTradingVolume {
	public long getId();

	public void setId(long id);

	/**
	 * 当前统计月的平均每日入钞箱金额
	 * @return
	 */
	public double getMonthAmtInAvg();

	/**
	 * 当前统计月的平均每日入钞箱金额
	 * @param monthAmtInAvg
	 */
	public void setMonthAmtInAvg(double monthAmtInAvg);

	/**
	 * 当前统计月的平均每日出钞箱金额
	 * @return
	 */
	public double getMonthAmtOutAvg();

	/**
	 * 当前统计月的平均每日出钞箱金额
	 * @param monthAmtOutAvg
	 */
	public void setMonthAmtOutAvg(double monthAmtOutAvg);

	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	/**
	 * 当前统计月
	 * @return
	 */
	public int getTransMonth();

	/**
	 * 当前统计月
	 * @param transMonth
	 */
	public void setTransMonth(int transMonth);

	/**
	 * 当前月同比平均每日入钞箱金额
	 * @return
	 */
	public double getLastYearAmtInAvg();

	/**
	 * 当前月同比平均每日入钞箱金额
	 * @param lastYearAmtInAvg
	 */
	public void setLastYearAmtInAvg(double lastYearAmtInAvg);

	/**
	 * 当前月同比平均每日出钞箱金额
	 * @return
	 */
	public double getLastYearAmtOutAvg();

	/**
	 * 当前月同比平均每日出钞箱金额
	 * @param lastYearAmtOutAvg
	 */
	public void setLastYearAmtOutAvg(double lastYearAmtOutAvg);
}
