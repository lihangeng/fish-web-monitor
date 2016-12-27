package com.yihuacomputer.fish.api.report.fault.etl;

import java.util.List;

/**
 * 故障抽取服务
 */
public interface IFaultEtlService {
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public IFaultWeek getWeek(long weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month
	 * @return
	 */
	public IFaultMonth getMonth(long month);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultClassifyWeek> getClassifyWeek(long weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month
	 * @return
	 */
	public List<IFaultClassifyMonth> getClassifyMonth(long month);
	
	/**
	 * 获取某周的统计
	 * @param weekOfYear
	 * @return
	 */
	public List<IFaultDurationWeek> getDurationWeek(long weekOfYear);
	
	/**
	 * 获取某月的统计
	 * @param month
	 * @return
	 */
	public List<IFaultDurationMonth> getDurationMonth(long month);
	
	/**
	 * 保存每周故障数
	 * @param faultWeek
	 * @return
	 */
	public IFaultWeek saveByFaultWeek(IFaultWeek faultWeek);
	
	/**
	 * 保存每月故障数
	 * @param faultMonth
	 * @return
	 */
	public IFaultMonth saveByFaultMonth(IFaultMonth faultMonth);
	
	/**
	 * 保存每周故障分类数
	 * @param faultClassifyWeek
	 * @return
	 */
	public IFaultClassifyWeek saveByFaultClassifyWeek(IFaultClassifyWeek faultClassifyWeek);
	
	/**
	 * 保存每月故障分类数
	 * @param faultClassifyMonth
	 * @return
	 */
	public IFaultClassifyMonth saveByFaultClassifyMonth(IFaultClassifyMonth faultClassifyMonth);
	
	/**
	 * 保存每周故障持续时长数据
	 * @param faultDurationWeek
	 * @return
	 */
	public IFaultDurationWeek saveByFaultDurationWeek(IFaultDurationWeek faultDurationWeek);
	
	/**
	 * 保存每月故障持续时长数据
	 * @param faultDurationMonth
	 * @return
	 */
	public IFaultDurationMonth saveByFaultDurationMonth(IFaultDurationMonth faultDurationMonth);
}
