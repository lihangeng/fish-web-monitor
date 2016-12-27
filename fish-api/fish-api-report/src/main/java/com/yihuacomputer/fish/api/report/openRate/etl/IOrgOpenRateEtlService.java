/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.List;


/**
 * 根据网点计算平均开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IOrgOpenRateEtlService {
	
	/**
	 * 获取某周的统计，排名靠前的网点
	 * @param weekOfYear
	 * @param limit
	 * @return
	 */
	public List<IOrgOpenRateWeek> getTopOrgWeek(long weekOfYear,int limit);
	
	/**
	 * 获取某月的统计，排名靠前的网点
	 * @param month
	 * @param limit
	 * @return
	 */
	public List<IOrgOpenRateMonth> getTopOrgMonth(long month,int limit);
	
	/**
	 * 获取某周的统计（获取倒数limit个网点）
	 * @param weekOfYear
	 * @param limit
	 * @return
	 */
	public List<IOrgOpenRateWeek> getLastOrgWeek(long weekOfYear,int limit);
	
	/**
	 * 获取倒数limit个网点的
	 * @param month
	 * @param limit
	 * @return
	 */
	public List<IOrgOpenRateMonth> getLastOrgMonth(long month,int limit);
	
	/**
	 * 保存每周数据
	 * @param orgOpenRateWeek
	 * @return
	 */
	public IOrgOpenRateWeek saveByWeek(IOrgOpenRateWeek orgOpenRateWeek);
	
	/**
	 * 保存每月数据
	 * @param orgOpenRateMonth
	 * @return
	 */
	public IOrgOpenRateMonth saveByMonth(IOrgOpenRateMonth orgOpenRateMonth);
}
