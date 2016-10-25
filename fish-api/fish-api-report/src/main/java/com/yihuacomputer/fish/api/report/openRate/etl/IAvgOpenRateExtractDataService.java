package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;

public interface IAvgOpenRateExtractDataService {

	/**
	 * 根据每台设备的开机率数据抽取数据
	 * @param date 
	 */
	public void extractByDay(Date date);
	
	/**
	 * 抽过去上周的统计数据
	 * @param date 上周的时间
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 根据每台设备的开机率数据抽取每月开机率
	 * @param date  上月的时间
	 */
	public void extractByMonth(Date date);
}
