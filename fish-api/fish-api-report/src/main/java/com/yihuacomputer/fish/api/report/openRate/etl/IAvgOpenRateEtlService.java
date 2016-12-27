/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.List;


/**
 * 计算所有设备的平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IAvgOpenRateEtlService {
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Object [] getWeekTotal(long weekOfYear);
	
	/**
	 * 获得月总的统计数据
	 * @param month
	 * @return
	 */
	public Object [] getMonthTotal(long month);
	
	/**
	 * 某一段时间内的每日平均开机率
	 * @param start yyyyMMdd开始时间
	 * @param end yyyyMMdd 结束时间
	 * @return
	 */
	public List<IAvgDayOpenRate> getAvgDays(long start, long end);
	
	/**
	 * 保存每日数据
	 * @param avgDayOpenRate
	 * @return
	 */
	public IAvgDayOpenRate saveByDay(IAvgDayOpenRate avgDayOpenRate);
	
	/**
	 * 保存每周数据
	 * @param avgWeekOpenRate
	 * @return
	 */
	public IAvgWeekOpenRate saveByWeek(IAvgWeekOpenRate avgWeekOpenRate);
	
	/**
	 * 保存每月数据
	 * @param avgMonthOpenRate
	 * @return
	 */
	public IAvgMonthOpenRate saveByMonth(IAvgMonthOpenRate avgMonthOpenRate);
	
	
}
