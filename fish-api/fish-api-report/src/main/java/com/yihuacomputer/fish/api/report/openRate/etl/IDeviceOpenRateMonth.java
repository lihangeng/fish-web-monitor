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
	void setDate(long date);
	
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
