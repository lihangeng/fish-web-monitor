package com.yihuacomputer.fish.api.atmlog;

import java.util.List;

/**
 * 客户信息
 * @author YiHua
 *
 */
public interface ICustomerCycle {
	/**
	 * 客户插卡时间
	 * @return
	 */
	public String getDateTime();

	/**
	 * 交易账号
	 * @return
	 */
	public String getAccount();

	/**
	 * 交易列表信息
	 * @return
	 */
	public List<ITransCycle> getTrans();
	
}
