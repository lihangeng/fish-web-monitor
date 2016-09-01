package com.yihuacomputer.fish.api.report.trans.etl;

import java.util.Date;
import java.util.List;

/**
 * 根据交易类型抽取服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface ITransTypeEtlService {
	/**
	 * 根据天抽取
	 * @param date
	 */
	public void extractByDay(Date date);
	
	/**
	 * 根据天抽取的数据抽取周数据
	 * @param date
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 根据天抽取的数据抽取月数据
	 * @param date
	 */
	public void extractByMonth(Date date);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<ITransTypeWeek> getWeek(int weekOfYear);
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getWeekTotal(int weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<ITransTypeMonth> getMonth(int month);
	
	/**
	 * 获得月总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getMonthTotal(int month);
	
}
