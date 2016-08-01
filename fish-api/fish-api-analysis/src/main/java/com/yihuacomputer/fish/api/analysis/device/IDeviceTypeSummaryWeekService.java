package com.yihuacomputer.fish.api.analysis.device;

import java.util.Date;
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

	/**
	 * 加载基础数据
	 * @param date 执行时间点 40周执行39周汇总，此时传入的日期为40周周一,得出的是39新增及汇总数据
	 */
	void loadBaseDate(Date date);
}
