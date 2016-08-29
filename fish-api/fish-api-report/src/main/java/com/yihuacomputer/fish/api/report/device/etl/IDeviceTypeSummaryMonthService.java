package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author GQ
 * 设备按型号进行品牌月统计(cds6040w,cds6040t...)服务
 */
public interface IDeviceTypeSummaryMonthService {
	IDeviceTypeSummaryMonth make();
	IDeviceTypeSummaryMonth update(IDeviceTypeSummaryMonth dcsm);
	IDeviceTypeSummaryMonth save(IDeviceTypeSummaryMonth dcsm);
	List<IDeviceTypeSummaryMonth> list(IFilter filter);
	IDeviceTypeSummaryMonth get(String catalogName,String date);
	/**
	 * 根据日期得到品牌统计信息
	 * @param date
	 * @return
	 */
	Map<String,IDeviceTypeSummaryMonth> get(String date);

	/**
	 * 加载基础数据
	 * @param date 执行时间点 7月份执行6月份汇总，此时传入的日期为7月份,得出的是2016-06月份的新增数据
	 */
	void loadBaseDate(Date date);
}
