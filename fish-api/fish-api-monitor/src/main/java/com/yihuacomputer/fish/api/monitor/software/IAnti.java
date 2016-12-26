package com.yihuacomputer.fish.api.monitor.software;

/**
 * 系统杀毒软件信息
 * @author YiHua
 *
 */
public interface IAnti {
	/**
	 * 病毒软件版本
	 * 
	 * @return String
	 */
	public String getAntiVer();

	/**病毒软件名称
	 * @return
	 */
	public String getAntiName();
}
