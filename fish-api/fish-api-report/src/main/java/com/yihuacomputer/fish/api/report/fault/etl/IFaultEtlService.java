package com.yihuacomputer.fish.api.report.fault.etl;

import java.util.Date;
import java.util.List;

/**
 * 故障抽取服务
 */
public interface IFaultEtlService {
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractStatusByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractStatusByMonth(Date date);
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractFaultClassifyByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractFaultClassifyByMonth(Date date);
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractDurationByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractDurationByMonth(Date date);
	
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public IFaultWeek getWeek(int weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param weekOfYear
	 * @return
	 */
	public IFaultMonth getMonth(int month);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultClassifyWeek> getClassifyWeek(int weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultClassifyMonth> getClassifyMonth(int month);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultDurationWeek> getDurationWeek(int weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultDurationMonth> getDurationMonth(int month);
}
