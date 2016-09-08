package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
/**
 * @author GQ
 * 设备按分类进行分类月统计(CRS,ATM...)服务
 */
public interface IDeviceCatalogSummaryMonthService {
	IDeviceCatalogSummaryMonth make();
	IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm);
	IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm);
	List<IDeviceCatalogSummaryMonth> list(IFilter filter);
	IDeviceCatalogSummaryMonth get(String catalogName,String date);
	/**
	 * 根据日期得到分类统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceCatalogSummaryMonth> get(String date);

	/**
	 * 加载基础数据
	 * @param date 执行时间点 7月份执行6月份汇总，此时传入的日期为7月份,得出的是2016-06月份的新增数据
	 */
	void loadBaseData(Date date);
	
	/**
	 * 月设备趋势
	 * @param month
	 * @param months
	 * @return
	 */
	Map<String,List<IDeviceCatalogSummaryMonth>> getMonth(int month,int months);
	
	/**
	 * 新增设备和报废设备趋势图
	 */
	List<IDeviceCatalogSummaryMonth> getAddAndScrp(int month,int lastMonth);
}
