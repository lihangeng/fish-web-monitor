package com.yihuacomputer.fish.api.report.device.etl;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author GQ
 * 设备按型号进行分类周统计(cds6040w,cds6040t...)服务
 */
public interface IDeviceTypeSummaryWeekService {
	IDeviceTypeSummaryWeek make();
	IDeviceTypeSummaryWeek update(IDeviceTypeSummaryWeek dcsm);
	IDeviceTypeSummaryWeek save(IDeviceTypeSummaryWeek dcsm);
	List<IDeviceTypeSummaryWeek> list(IFilter filter);
	IDeviceTypeSummaryWeek get(String catalogName,String date);
	/**
	 * 根据日期得到周型号统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceTypeSummaryWeek> get(String date);

}
