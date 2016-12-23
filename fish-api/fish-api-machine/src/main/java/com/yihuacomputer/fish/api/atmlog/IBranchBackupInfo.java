package com.yihuacomputer.fish.api.atmlog;

/**
 * @author YiHua
 *
 */
public interface IBranchBackupInfo {

	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * @return
	 */
	public long setId();
	
	/**
	 * 机构编号
	 * @return
	 */
	public String getOrgId();
	
	/**
	 * @param orgId
	 */
	public void setOrgId(String orgId);
	
	/**
	 * 备份日期
	 * @return
	 */
	public String getDate();

	/**
	 * @param date
	 */
	public void setDate(String date);

	/**
	 * 当日备份结果
	 * @return
	 */
	public DayBackupResult getResult();

	/**
	 * @param result
	 */
	public void setResult(DayBackupResult result);

	/**
	 * 执行时间
	 * @return
	 */
	public String getDoTime();

	/**
	 * @param doTime
	 */
	public void setDoTime(String doTime);

	/**
	 * 结束时间
	 * @return
	 */
	public String getEndTime();

	/**
	 * @param endTime
	 */
	public void setEndTime(String endTime);
	
	/**
	 * 日志设备数量累计
	 * @return
	 */
	public int getDeviceCount();

	/**
	 * @param deviceCount
	 */
	public void setDeviceCount(int deviceCount);
	
	/**
	 * 上传服务器结果
	 * @param uploadRet
	 */
	public void setUploadRet(DayBackupResult uploadRet);
	
	/**
	 * @return
	 */
	public DayBackupResult getUploadRet();

}
