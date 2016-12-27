package com.yihuacomputer.fish.api.report.fault.etl;

/**
 * 每周故障数据
 * @author xuxiang
 *
 */
public interface IFaultWeek {
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
	 * 获得未关闭的故障数量
	 * @return
	 */
	long getOpenCount();
	/**
	 * 
	 * @param openCount
	 */
	void setOpenCount(long openCount);
	
	/**
	 * 获得关闭故障的数量
	 * @return
	 */
	long getCloseCount();
	
	/**
	 * 
	 * @param closeCount
	 */
	void setCloseCount(long closeCount);
}
