package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 每周的设备开机率数据
 * @author xuxiang
 *
 */
public interface IDeviceTypeOpenRateWeek extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyWW
	 * 如201615
	 * @return
	 */
	String getDate();
	void setDate(String date);
	
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
	 * 设备类型ID
	 * @return
	 */
	long getTypeId();
	void setTypeId(long typeId);
	
	/**
	 * 设备类型名称
	 * @return
	 */
	String getDevType();
	void setDevType(String devType);
}
