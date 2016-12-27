package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface IDeviceExtractDataMonthService {

	/**
	 * 加载基础数据
	 * @param date 执行时间点 7月份执行6月份汇总，此时传入的日期为7月份,得出的是2016-06月份的新增数据
	 */
	void loadCatalogBaseData(Date date);
	
	/**
	 * 加载基础数据
	 * @param date 执行时间点 7月份执行6月份汇总，此时传入的日期为7月份,得出的是2016-06月份的新增数据
	 */
	void loadTypeBaseData(Date date);
}
