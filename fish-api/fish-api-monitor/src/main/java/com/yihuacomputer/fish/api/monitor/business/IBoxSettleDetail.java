package com.yihuacomputer.fish.api.monitor.business;
/**
 * 钞箱结账详情
 * @author YiHua
 *
 */
public interface IBoxSettleDetail {
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
	public long getBoxLeftAmt();
}
