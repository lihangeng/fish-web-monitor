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
	
	/**
	 * 设备编号
	 * @return
	 */
	String getTerminalId();
	/**
	 * @param terminalId
	 */
	void setTerminalId(String terminalId);
	/**
	 * 机构编号
	 * @return
	 */
	String getOrgCode();
	/**
	 * @param orgCode
	 */
	void setOrgCode(String orgCode);
	
	/**
	 * 机构名称
	 * @return
	 */
	String getOrgName();
	/**
	 * @param orgName
	 */
	void setOrgName(String orgName);
	
	/**
	 * 设备类型ID
	 * @return
	 */
	long getTypeId();
	/**
	 * @param typeId
	 */
	void setTypeId(long typeId);
	
	/**
	 * 设备类型名称
	 * @return
	 */
	String getDevType();
	/**
	 * @param devType
	 */
	void setDevType(String devType);
}
