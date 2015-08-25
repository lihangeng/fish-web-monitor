package com.yihuacomputer.fish.api.report.base;

/**
 * 当天现金数据统计
 * @author YiHua
 *
 */
public interface ICashDetailRpt {

	/**
	 * 机构名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 取款金额
	 * @return
	 */
	public double getCwdAmt();
	
	/**
	 * 存款金额
	 * @return
	 */
	public double getDepAmt();
	
	/**
	 * 循环金额
	 * @return
	 */
	public double getCrsAmt();
}
