/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;
import java.util.List;


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
	
	/**
	 * 周数据：按照设备统计的开机率，较好设备Top10
	 * @param weekOfYear
	 * @return
	 */
	public List<IDeviceOpenRateWeek> getTopDeviceWeek(long weekOfYear,int limit);
	
	/**
	 * 获取某月数据：按照设备统计的开机率，较好设备Top10
	 * @param weekOfYear
	 * @return
	 */
	public List<IDeviceOpenRateMonth> getTopDeviceMonth(long month,int limit);
	
	/**
	 * 获取某周的统计：4.	按照设备统计的开机率，较查设备Top10
	 * @param weekOfYear
	 * @return
	 */
	public List<IDeviceOpenRateWeek> getLastDeviceWeek(long weekOfYear,int limit);
	
	/**
	 * 获取某月的统计：4.	按照设备统计的开机率，较查设备Top10
	 * @param weekOfYear
	 * @return
	 */
	public List<IDeviceOpenRateMonth> getLastDeviceMonth(long month,int limit);
	
	/**
	 * 保存每周设备开机率
	 */
	public IDeviceOpenRateWeek saveByWeek(IDeviceOpenRateWeek deviceOpenRateWeek);
	
	/**
	 * 保存每月开机率
	 */
	public IDeviceOpenRateMonth saveByMonth(IDeviceOpenRateMonth deviceOpenRateMonth);

}
