package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;

public interface IDeviceExtractDataWeekService {

	/**
	 * 加载基础数据
	 * @param date 执行时间点 40周执行39周汇总，此时传入的日期为40周周一,得出的是39新增及汇总数据
	 */
	void loadCatalogBaseData(Date date);
	
	/**
	 * 加载基础数据
	 * @param date 执行时间点 40周执行39周汇总，此时传入的日期为40周周一,得出的是39新增及汇总数据
	 */
	void loadTypeBaseData(Date date);
}
