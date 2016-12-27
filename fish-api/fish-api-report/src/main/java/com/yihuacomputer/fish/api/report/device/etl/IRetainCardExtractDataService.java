package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface IRetainCardExtractDataService {

	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractByMonth(Date date);
}
