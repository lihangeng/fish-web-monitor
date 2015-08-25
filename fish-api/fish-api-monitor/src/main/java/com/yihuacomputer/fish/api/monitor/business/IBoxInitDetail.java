package com.yihuacomputer.fish.api.monitor.business;
/**
 * 钞箱清机详情
 * @author YiHua
 *
 */
public interface IBoxInitDetail {
	/**
	 * 钞箱ID
	 * @return
	 */
	public String getBoxId();

	/**
	 * 币种
	 * @return
	 */
	public String getBoxCurrency();

	/**
	 * 金额
	 * @return
	 */
	public long getBoxInitAmt();
}
