package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;

public interface IDeviceTypeOpenRateExtractDataService {

	/**
	 * 抽取每周的设备型号开机率
	 * @param date 上周
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 抽取上周的设备型号统计数据
	 * @param date 上个月
	 */
	public void extractByMonth(Date date);
}
