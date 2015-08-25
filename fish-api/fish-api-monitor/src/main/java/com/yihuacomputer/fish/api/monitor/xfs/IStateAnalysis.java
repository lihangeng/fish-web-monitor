package com.yihuacomputer.fish.api.monitor.xfs;

/**
 * 状态码解析信息
 * @author YiHua
 *
 */
public interface IStateAnalysis {
	/**
	 * 状态描述
	 * @return
	 */
	public String getDescription();
	/**
	 * 状态故障解决方案
	 * @return
	 */
	public String getSolution();
}
