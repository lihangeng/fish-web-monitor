package com.yihuacomputer.fish.api.fault;

/**
 * 故障码过滤器
 * @author Yihua
 *
 */
public interface IFaultFilter {

	/**
	 * 初始化故障过滤码
	 */
	public void initFilterCode();
	
	/**
	 * 判断该故障码是否需要过滤
	 * @param faultCode
	 * @return
	 */
	public boolean isFilter(IFaultCode faultCode);
}
