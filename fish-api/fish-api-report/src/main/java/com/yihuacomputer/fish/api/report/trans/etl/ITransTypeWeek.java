package com.yihuacomputer.fish.api.report.trans.etl;

/**
 * 每日交易类型统计数据
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface ITransTypeWeek {
	/**
	 * 
	 * @return
	 */
	long getId();

	/**
	 * 获得统计的日期
	 * 
	 * @return yyyyww
	 */
	long getDate();

	/**
	 * 
	 * @param date yyyyww
	 */
	void setDate(long date);

	/**
	 * 获得交易码
	 * 
	 * @return
	 */
	String getTransCode();

	/**
	 * 
	 * @param transCode
	 */
	void setTransCode(String transCode);

	/**
	 * 获得交易的次数
	 * 
	 * @return
	 */
	long getTransCount();

	/**
	 * 
	 * @param transCount
	 */
	void setTransCount(long transCount);

	/**
	 * 获得交易的金额
	 * 
	 * @return
	 */
	double getTransAmount();

	/**
	 * 
	 * @param transAmount
	 */
	void setTransAmount(double transAmount);
}
