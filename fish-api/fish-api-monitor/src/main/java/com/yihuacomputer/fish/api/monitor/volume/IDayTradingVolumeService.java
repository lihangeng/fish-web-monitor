package com.yihuacomputer.fish.api.monitor.volume;


public interface IDayTradingVolumeService {
	/**
	 * 计算前一天的交易金额
	 */
	void generalDayTradingVolume();
	/**
	 * 计算指定日期交易金额
	 * @param date (format:yyyyMMdd)
	 */
	void generalDayTradingVolumeByDate(String date);
	IDayTradingVolume make();
	IDayTradingVolume save(IDayTradingVolume dayTradingVolume);
	IDayTradingVolume update(IDayTradingVolume dayTradingVolume);
	void remove(IDayTradingVolume dayTradingVolume);
}
