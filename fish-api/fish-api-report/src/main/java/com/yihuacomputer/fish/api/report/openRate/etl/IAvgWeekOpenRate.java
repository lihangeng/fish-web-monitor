package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 
 * @author xuxiang
 *
 */
public interface IAvgWeekOpenRate  extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyww
	 * 如201615
	 * @return
	 */
	long getDate();
	/**
	 * @param date
	 */
	void setDate(long date);
	
	/**
	 * 周开始时间yyyyMMdd
	 * @return
	 */
	String getStartDate();
	/**
	 * @param startDate
	 */
	void setStartDate(String startDate);
	/**
	 * 周结束时间yyyyMMdd
	 * @return
	 */
	String getEndDate();
	/**
	 * @param endDate
	 */
	void setEndDate(String endDate);
}
