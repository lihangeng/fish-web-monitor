package com.yihuacomputer.fish.api.report.base;

public interface IEveryMonthFaultCount {

	/**
	 * ID
	 * 
	 * @return
	 */
	public long getId();

	public void setId(long id);

	/**
	 * 设备号
	 * 
	 * @return
	 */
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	/**
	 * 故障模块
	 * 
	 * @return
	 */
	public String getDevMod();

	public void setDevMod(String devMod);

	/**
	 * 故障分类
	 * 
	 * @return
	 */
	public String getClassifyId();

	public void setClassifyId(String classifyId);

	/**
	 * 故障日期
	 * 
	 * @return
	 */
	public long getFaultDate();

	public void setFaultDate(long faultDate);

	/**
	 * 故障次数
	 * 
	 * @return
	 */
	public long getFaultCount();

	public void setFaultCount(long faultCount);

}
