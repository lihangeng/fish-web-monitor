package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 每月所有所有设备开机率
 * @author xuxiang
 *
 */
public interface IAvgMonthOpenRate extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyMM
	 * 如201608
	 * @return
	 */
	String getDate();
	/**
	 * 
	 * @param date
	 */
	void setDate(String date);
	
}
