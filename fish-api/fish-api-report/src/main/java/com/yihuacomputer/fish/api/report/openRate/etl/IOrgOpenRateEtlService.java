/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;


/**
 * 根据网点计算平均开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IOrgOpenRateEtlService {
	
	/**
	 * 抽取每周的网点开机率
	 * @param date 
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 抽取上周的网点统计数据
	 * @param date
	 */
	public void extractByMonth(Date date);
}
