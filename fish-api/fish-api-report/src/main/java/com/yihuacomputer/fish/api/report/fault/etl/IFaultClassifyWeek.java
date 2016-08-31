package com.yihuacomputer.fish.api.report.fault.etl;

/**
 * 每周故障分类数据
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IFaultClassifyWeek {
	/**
	 * 
	 * @return
	 */
	long getId();
	/**
	 * 获得统计的日期
	 * 
	 * @return yyyyww
	 */
	long getDate();

	/**
	 * 
	 * @param date yyyyww
	 */
	void setDate(long date);
	
	/**
	 * 获得分类Id
	 */
	long getClassifyId();
	/**
	 * 
	 * @param classifyId
	 */
	void setClassifyId(long classifyId);
	
	/**
	 * 
	 * @return
	 */
	String getClassifyName();
	/**
	 * 
	 * @param classifyName
	 */
	void setClassifyName(String classifyName);
	
	/**
	 * 获得关闭故障的数量
	 */
	long getCount();
	
	/**
	 * 
	 * @param closeCount
	 */
	void setCount(long closeCount);
}
