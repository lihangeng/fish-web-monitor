package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 设备型号的开机率数据
 * @author xuxiang
 *
 */
public interface IDeviceTypeOpenRateMonth extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyMM
	 * 如201611
	 * @return
	 */
	long getDate();
	/**
	 * @param date
	 */
	void setDate(long date);
	
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
