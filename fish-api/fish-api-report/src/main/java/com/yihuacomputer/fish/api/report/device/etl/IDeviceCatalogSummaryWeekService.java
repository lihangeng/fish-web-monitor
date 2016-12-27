package com.yihuacomputer.fish.api.report.device.etl;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author GQ
 * 设备按分类进行分类周统计(CRS,ATM...)服务
 */
public interface IDeviceCatalogSummaryWeekService {
	/**
	 * @return
	 */
	IDeviceCatalogSummaryWeek make();
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceCatalogSummaryWeek update(IDeviceCatalogSummaryWeek dcsm);
	/**
	 * @param dcsm
	 * @return
	 */
	IDeviceCatalogSummaryWeek save(IDeviceCatalogSummaryWeek dcsm);
	/**
	 * @param filter
	 * @return
	 */
	List<IDeviceCatalogSummaryWeek> list(IFilter filter);
	/**
	 * @param catalogName
	 * @param date
	 * @return
	 */
	IDeviceCatalogSummaryWeek get(String catalogName,String date);
	/**
	 * 根据日期得到分类统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceCatalogSummaryWeek> get(String date);

	/**
	 * 周设备趋势
	 * @param weekOfYear
	 * @param days 几周前的，如 4，四周前
	 * @return String 为设备类型，如ATM
	 */
	Map<String,List<IDeviceCatalogSummaryWeek>> getWeek(int weekOfYear,int days);
	/**
	 * 新增设备和报废设备趋势图
	 * @param weekOfYear
	 * @param lastWeek
	 * @return
	 */
	List<IDeviceCatalogSummaryWeek> getAddAndScrp(int weekOfYear,int lastWeek);
	

}
