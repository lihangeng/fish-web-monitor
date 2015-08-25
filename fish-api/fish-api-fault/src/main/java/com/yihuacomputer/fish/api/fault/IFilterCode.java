package com.yihuacomputer.fish.api.fault;

/**
 * 过滤代码
 * @author Yihua
 *
 */
public interface IFilterCode {

	public long getId();
	
	/**
	 * 故障码
	 * @return
	 */
	public String getCode();
	
	/**
	 * 描述
	 * @return
	 */
	public String getRemark();
}
