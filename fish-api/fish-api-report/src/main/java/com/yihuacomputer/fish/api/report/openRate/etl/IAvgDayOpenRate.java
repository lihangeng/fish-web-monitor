package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 每日所有设备平均开机率
 * @author xuxiang
 *
 */
public interface IAvgDayOpenRate extends IOpenRateColumn	{
	
	/**
	 * 获得统计的日期,格式为yyyyMMdd
	 * 如20160830
	 * @return
	 */
	long getDate();
	/**
	 * @param date
	 */
	void setDate(long date);
}
