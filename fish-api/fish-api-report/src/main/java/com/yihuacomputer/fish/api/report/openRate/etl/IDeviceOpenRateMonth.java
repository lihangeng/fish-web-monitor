package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 每台设备的月度开机率
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IDeviceOpenRateMonth extends IOpenRateColumn {
	/**
	 * 获得统计的日期,格式为yyyyMM
	 * 如201608
	 * @return
	 */
	long getDate();
	/**
	 * @param date
	 */
	void setDate(long date);
	
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
