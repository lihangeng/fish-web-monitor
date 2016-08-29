/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;


/**
 * 计算所有设备的平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IAvgOpenRateService {
	/**
	 * 根据每台设备的开机率数据抽取数据
	 * @param date yyyy-MM-dd
	 */
	public void extractByDay(String date);
	
	/**
	 * 抽过去上周的统计数据
	 * @param weekOfYear 一年当中的第几周
	 */
	public void extractByWeek(int weekOfYear);
	
	/**
	 * 根据每台设备的开机率数据抽取每月开机率
	 * @param date yyyy-MM
	 */
	public void extractByMonth(String date);
}
