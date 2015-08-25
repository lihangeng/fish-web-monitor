package com.yihuacomputer.fish.api.atmlog;

import java.util.List;

/**
 * 周期信息
 * @author YiHua
 *
 */
public interface IAtmCycle {
	
	/**
	 * 加钞ID
	 * @return
	 */
	public String getCashInId();

	/**
	 * 加钞时间
	 * @return
	 */
	public String getDateTime();

	/**
	 * 客户交易列表
	 * @return
	 */
	public List<ICustomerCycle> getCustomers() ;

	
}
