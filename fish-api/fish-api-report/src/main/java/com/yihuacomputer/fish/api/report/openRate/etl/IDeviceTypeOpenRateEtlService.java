/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;
import java.util.List;


/**
 * 根据设备型号计算平均开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IDeviceTypeOpenRateEtlService {
	
	/**
	 * 抽取每周的设备型号开机率
	 * @param date 上周
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 抽取上周的设备型号统计数据
	 * @param date 上个月
	 */
	public void extractByMonth(Date date);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear yyyyww
	 * @return
	 */
	public List<IDeviceTypeOpenRateWeek> getDeviceTypeWeek(int weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month yyyyMM
	 * @return
	 */
	public List<IDeviceTypeOpenRateMonth> getDeviceTypeMonth(int month);
}
