package com.yihuacomputer.fish.api.report.trans.etl;

import java.util.List;

/**
 * 根据交易类型抽取服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface ITransTypeEtlService {
	/**
	 * 获取某周的统计
	 * @param weekOfYear yyyyww
	 * @return
	 */
	public List<ITransTypeWeek> getWeek(long weekOfYear);
	
	/**
	 * 获得总的统计数据
	 * @param weekOfYear yyyyww
	 * @return
	 */
	public Long [] getWeekTotal(long weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month yyyyMM
	 * @return
	 */
	public List<ITransTypeMonth> getMonth(long month);
	
	/**
	 * 获得月总的统计数据 
	 * @param month yyyyMM
	 * @return
	 */
	public Long [] getMonthTotal(long month);
	
	/**
	 * 保存交易类型/日
	 */
	public ITransTypeDay saveByDay(ITransTypeDay transTypeDay);
	
	/**
	 * 保存交易类型/周
	 */
	public ITransTypeWeek saveByWeek(ITransTypeWeek transTypeWeek);
	
	/**
	 * 保存交易类型/月
	 */
	public ITransTypeMonth saveByMonth(ITransTypeMonth transTypeMonth);
	
}
