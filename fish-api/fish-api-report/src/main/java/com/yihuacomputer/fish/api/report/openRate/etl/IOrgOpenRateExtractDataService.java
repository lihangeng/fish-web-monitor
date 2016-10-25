package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;

public interface IOrgOpenRateExtractDataService {

	/**
	 * 抽取每周的网点开机率
	 * @param date 
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 抽取上周的网点统计数据
	 * @param date
	 */
	public void extractByMonth(Date date);
}
