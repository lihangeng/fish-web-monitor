/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;


/**
 * 根据网点计算平均开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IOrgOpenRateService {
	
	/**
	 * 抽取每周的网点开机率
	 * @param date yyyy-MM
	 */
	public void extractByWeek(int weekOfYear);
	
	/**
	 * 抽取上周的网点统计数据
	 * @param weekOfYear 一年当中的第几周
	 */
	public void extractByMonth(int date);
}
