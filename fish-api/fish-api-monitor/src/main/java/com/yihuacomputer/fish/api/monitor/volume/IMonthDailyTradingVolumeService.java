package com.yihuacomputer.fish.api.monitor.volume;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IMonthDailyTradingVolumeService {
	/**
	 * 计算前一月日均交易金额和当前月去年日均交易额
	 */
	void generalMonthDailyTradingVolume();
	/**
	 * 计算指定月的前一月日均交易金额和指定月去年日均交易额
	 * @param date yyyyMMdd
	 */
	void generalMonthDailyTradingVolumeByDate(String date);
	IMonthDailyTradingVolume make();
	IMonthDailyTradingVolume save(IMonthDailyTradingVolume monthDailyTradingVolume);
	IMonthDailyTradingVolume update(IMonthDailyTradingVolume monthDailyTradingVolume);
	void remove(IMonthDailyTradingVolume monthDailyTradingVolume);
	List<IMonthDailyTradingVolume> list(IFilter filter);
}
