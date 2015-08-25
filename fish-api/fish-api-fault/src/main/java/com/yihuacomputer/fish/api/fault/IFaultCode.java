package com.yihuacomputer.fish.api.fault;

/**
 * 故障码信息
 * @author Yihua
 *
 */
public interface IFaultCode {

	/**
	 * 故障码
	 * @return
	 */
	public String getFaultCode();
	
	/**
	 * 厂商故障码
	 * @return
	 */
	public String getHwCode();
}
