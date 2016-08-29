/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;


/**
 * 每日计算平均开机率服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IAvgDayOpenRateService {
	/**
	 * 根据每台设备的开机率数据抽取数据
	 * @param date yyyy-MM-dd
	 */
	public void extract(String date);
}
