package com.yihuacomputer.fish.api.monitor.software;

/**
 * SP信息
 * @author YiHua
 *
 */
public interface ISP {
	/**
	 * SP版本号
	 * 
	 * @return String
	 */
	public String getSpVersion();

	/**
	 * SP补丁信息
	 * 
	 * @return String
	 */
	public String getSpPatch();

	/**
	 * 版本日期
	 * 
	 * @return String
	 */
	public String getSpDate();
}
