package com.yihuacomputer.fish.api.analysis.openrate;

/**
 * 每日所有设备平均开机率
 * @author xuxiang
 *
 */
public interface IAvgDayOpenRate {
	long geId();
	/**
	 * 获得统计的日期,格式为yyyyMMdd
	 * 如20160830
	 * @return
	 */
	long getDate();
	void setDate(long date);
	
	
	
}
