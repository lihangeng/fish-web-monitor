package com.yihuacomputer.fish.api.report.device.etl;

/**
 * 吞卡统计数据
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IRetainCardMonth {
	/**
	 * 
	 * @return
	 */
	long getId();
	
	/**
	 * 获得设备型号
	 * @return
	 */
	String getDevType();
	/**
	 * 
	 * @param devType
	 */
	void setDevType(String devType);
	
	/**
	 * 获得统计日间
	 * @return yyyyMM
	 */
	long getDate();
	
	/**
	 * 
	 * @param date yyyyMM
	 */
	void setDate(long date);
	
	/**
	 * 获得该设备型号的设备数据
	 * @return
	 */
	long getDeviceCount();
	/**
	 * 
	 * @param deviceCount
	 */
	void setDeviceCount(long deviceCount);
	
	/**
	 * 获得吞卡数量
	 * @return
	 */
	long getRetainCount();
	/**
	 * 
	 * @param retainCount
	 */
	void setRetainCount(long retainCount);
	
	/**
	 * 获得上周的吞卡数量
	 * @return
	 */
	long getLastRetainCount();
	/**
	 * 
	 * @param astRetainCount
	 */
	void setLastRetainCount(long astRetainCount);
	
	/**
	 * 获得每台设备平均吞卡数量
	 * @return
	 */
	double getAvgCount();
}
