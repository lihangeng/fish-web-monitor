/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;


/**
 * 根据设备计算平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IDeviceOpenRateEtlService {
	/**
	 * 根据每台设备的开机率数据抽取每月开机率
	 * @param date yyyy-MM
	 */
	public void extractByMonth(Date date);
	
	/**
	 * 抽过去上周的统计数据
	 * @param date 统计周
	 */
	public void extractByWeek(Date Date);
}
