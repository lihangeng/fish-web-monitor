/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;
import java.util.List;


/**
 * 计算所有设备的平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IAvgOpenRateEtlService {
	/**
	 * 根据每台设备的开机率数据抽取数据
	 * @param date 
	 */
	public void extractByDay(Date date);
	
	/**
	 * 抽过去上周的统计数据
	 * @param date 上周的时间
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 根据每台设备的开机率数据抽取每月开机率
	 * @param date  上月的时间
	 */
	public void extractByMonth(Date date);
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Object [] getWeekTotal(long weekOfYear);
	
	/**
	 * 获得月总的统计数据
	 * @param weekOfYear
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
	
	
}
