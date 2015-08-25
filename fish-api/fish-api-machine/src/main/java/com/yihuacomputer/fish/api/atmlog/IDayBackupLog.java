package com.yihuacomputer.fish.api.atmlog;

public interface IDayBackupLog {

	/**
	 * 备份日期
	 * @return
	 */
	public String getDate();

	public void setDate(String date);

	/**
	 * 当日备份结果
	 * @return
	 */
	public DayBackupResult getResult();

	public void setResult(DayBackupResult result);

	/**
	 * 执行时间
	 * @return
	 */
	public String getDoTime();

	public void setDoTime(String doTime);

	/**
	 * 结束时间
	 * @return
	 */
	public String getEndTime();

	public void setEndTime(String endTime);
	
	/**
	 * 日志设备数量累计
	 * @return
	 */
	public int getDeviceCount();

	public void setDeviceCount(int deviceCount);

}
