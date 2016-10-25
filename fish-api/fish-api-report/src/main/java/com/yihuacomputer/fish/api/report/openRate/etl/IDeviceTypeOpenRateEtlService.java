/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.List;


/**
 * 根据设备型号计算平均开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IDeviceTypeOpenRateEtlService {
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear yyyyww
	 * @return
	 */
	public List<IDeviceTypeOpenRateWeek> getDeviceTypeWeek(long weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month yyyyMM
	 * @return
	 */
	public List<IDeviceTypeOpenRateMonth> getDeviceTypeMonth(long month);
	
	/**
	 * 保存某周数据
	 */
	IDeviceTypeOpenRateWeek saveByWeek(IDeviceTypeOpenRateWeek deviceTypeOpenRateWeek);
	
	/**
	 * 保存某月数据
	 */
	IDeviceTypeOpenRateMonth saveByMonth(IDeviceTypeOpenRateMonth deviceTypeOpenRateMonth);
}
