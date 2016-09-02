package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 每周的设备型号开机率数据
 * @author xuxiang
 *
 */
public interface IOrgOpenRateWeek extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyww
	 * 如201615
	 * @return
	 */
	long getDate();
	void setDate(long date);
	
	/**
	 * 周开始时间yyyyMMdd
	 * @return
	 */
	String getStartDate();
	void setStartDate(String startDate);
	/**
	 * 周结束时间yyyyMMdd
	 * @return
	 */
	String getEndDate();
	void setEndDate(String endDate);
	
	/**
	 * 机构编号
	 * @return
	 */
	String getOrgCode();
	void setOrgCode(String orgCode);
	
	/**
	 * 机构名称
	 * @return
	 */
	String getOrgName();
	void setOrgName(String orgName);
	
}
