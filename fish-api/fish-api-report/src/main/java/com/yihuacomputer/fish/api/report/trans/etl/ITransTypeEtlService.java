package com.yihuacomputer.fish.api.report.trans.etl;

import java.util.Date;

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
	
}
