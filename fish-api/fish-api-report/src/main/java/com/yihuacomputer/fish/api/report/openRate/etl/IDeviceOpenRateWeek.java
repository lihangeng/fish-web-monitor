package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 
 * @author xuxiang
 *
 */
public interface IDeviceOpenRateWeek extends IOpenRateColumn{
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
	 * 设备编号
	 * @return
	 */
	String getTerminalId();
	void setTerminalId(String terminalId);
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
