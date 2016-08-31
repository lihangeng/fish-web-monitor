package com.yihuacomputer.fish.api.report.fault.etl;

/**
 * 每周故障持续时间数据
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IFaultDurationMonth {
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
	 * @return 
	 */
	int getDuration();
	/**
	 * 
	 * @param duration
	 */
	void setDuration(int duration);
	
	/**
	 * 
	 * @return
	 */
	String getDurationName();
	
	/**
	 * 获得关闭故障的数量
	 * @return
	 */
	long getCount();
	
	/**
	 * 
	 * @param closeCount
	 */
	void setCount(long closeCount);
}
