package com.yihuacomputer.fish.api.report.device.etl;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
/**
 * @author GQ
 * 设备按分类进行分类月统计(CRS,ATM...)服务
 */
public interface IDeviceCatalogSummaryMonthService {
	/**
	 * @return
	 */
	IDeviceCatalogSummaryMonth make();
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm);
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm);
	/**
	 * @param filter
	 * @return
	 */
	List<IDeviceCatalogSummaryMonth> list(IFilter filter);
	/**
	 * @param catalogName
	 * @param date
	 * @return
	 */
	IDeviceCatalogSummaryMonth get(String catalogName,String date);
	/**
	 * 根据日期得到分类统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceCatalogSummaryMonth> get(String date);

	
	
	/**
	 * 月设备趋势
	 * @param month
	 * @param months
	 * @return
	 */
	Map<String,List<IDeviceCatalogSummaryMonth>> getMonth(int month,int months);
	
	/**
	 * 新增设备和报废设备趋势图
	 * @param month
	 * @param lastMonth
	 * @return
	 */
	List<IDeviceCatalogSummaryMonth> getAddAndScrp(int month,int lastMonth);
}
