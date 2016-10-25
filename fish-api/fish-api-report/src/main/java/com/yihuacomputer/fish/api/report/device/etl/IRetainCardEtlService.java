package com.yihuacomputer.fish.api.report.device.etl;

import java.util.List;

/**
 * 吞卡数据抽取服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IRetainCardEtlService {
	
	/**
	 * 获取某周的吞卡统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IRetainCardWeek> getWeek(long weekOfYear);
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getWeekTotal(long weekOfYear);
	
	/**
	 * 获取某月的吞卡统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IRetainCardMonth> getMonth(long month);
	
	/**
	 * 获得月总的统计数据
	 * @param weekOfYear
	 * @return
	 */
	public Long [] getMonthTotal(long month);
	
	/**
	 * 保存吞卡每周数据
	 */
	public IRetainCardWeek saveByWeek(IRetainCardWeek retainCardWeek);
	
	/**
	 * 保存吞卡每月数据
	 */
	public IRetainCardMonth saveByMonth(IRetainCardMonth retainCardMonth);
}
