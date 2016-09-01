package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;
import java.util.List;

/**
 * 吞卡数据抽取服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IRetainCardEtlService {
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractByMonth(Date date);
	
	/**
	 * 获取某周的吞卡统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IRetainCardWeek> getWeek(int weekOfYear);
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getWeekTotal(int weekOfYear);
	
	/**
	 * 获取某月的吞卡统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IRetainCardMonth> getMonth(int month);
	
	/**
	 * 获得月总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getMonthTotal(int month);
}
