package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author GQ
 * 设备按分类进行分类周统计(CRS,ATM...)服务
 */
public interface IDeviceCatalogSummaryWeekService {
	IDeviceCatalogSummaryWeek make();
	IDeviceCatalogSummaryWeek update(IDeviceCatalogSummaryWeek dcsm);
	IDeviceCatalogSummaryWeek save(IDeviceCatalogSummaryWeek dcsm);
	List<IDeviceCatalogSummaryWeek> list(IFilter filter);
	IDeviceCatalogSummaryWeek get(String catalogName,String date);
	/**
	 * 根据日期得到分类统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceCatalogSummaryWeek> get(String date);

	/**
	 * 加载基础数据
	 * @param date 执行时间点 40周执行39周汇总，此时传入的日期为40周周一,得出的是39新增及汇总数据
	 */
	void loadBaseDate(Date date);
}
