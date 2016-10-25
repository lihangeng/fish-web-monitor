package com.yihuacomputer.fish.api.report.trans.etl;

import java.util.Date;

public interface ITransTypeExtractDataService {

	/**
	 * 根据天抽取
	 * @param date
	 */
	public void extractByDay(Date date);
	
	/**
	 * 根据天抽取的数据抽取周数据
	 * @param date
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 根据天抽取的数据抽取月数据
	 * @param date
	 */
	public void extractByMonth(Date date);
}
