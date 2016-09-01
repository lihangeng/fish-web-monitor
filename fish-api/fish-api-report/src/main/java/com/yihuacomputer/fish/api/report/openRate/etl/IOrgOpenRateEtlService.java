/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

import java.util.Date;
import java.util.List;


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
	
	
	
	/**
	 * 获取某周的统计，排名靠前的网点
	 * @param weekOfYear
	 * @return
	 */
	public List<IOrgOpenRateWeek> getTopOrgWeek(int weekOfYear,int limit);
	
	/**
	 * 获取某月的统计，排名靠前的网点
	 * @param weekOfYear
	 * @return
	 */
	public List<IOrgOpenRateMonth> getTopOrgMonth(int month,int limit);
	
	/**
	 * 获取某周的统计（获取倒数limit个网点）
	 * @param weekOfYear
	 * @return
	 */
	public List<IOrgOpenRateWeek> getLastOrgWeek(int weekOfYear,int limit);
	
	/**
	 * 获取倒数limit个网点的
	 * @param weekOfYear
	 * @return
	 */
	public List<IOrgOpenRateMonth> getLastOrgMonth(int month,int limit);
}
