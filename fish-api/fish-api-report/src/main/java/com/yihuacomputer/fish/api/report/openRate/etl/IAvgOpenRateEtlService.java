/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;


/**
 * 计算所有设备的平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IAvgOpenRateEtlService {
	/**
	 * 根据每台设备的开机率数据抽取数据
	 * @param date yyyy-MM-dd
	 */
	public void extractByDay(Date date);
	
	/**
	 * 抽过去上周的统计数据
	 * @param date 统计的时间
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 根据每台设备的开机率数据抽取每月开机率
	 * @param date yyyy-MM
	 */
	public void extractByMonth(Date date);
}
