package com.yihuacomputer.fish.api.report.base;

/**
 * 吞卡明细模型
 *
 * @author YiHua
 *
 */
public interface IRetainCardRpt {

	/**
	 * 终端号
	 *
	 * @return
	 */
	public String getTerminalId();

	/**
	 * 机构名称
	 *
	 * @return
	 */
	public String getOrgName();

	/**
	 * 账户、卡号
	 *
	 * @return
	 */
	public String getAccount();

	/**
	 * 吞卡日期
	 *
	 * @return
	 */
	public String getRetainDate();

	/**
	 * 吞卡地址
	 *
	 * @return
	 */
	public String getAddress();

	/**
	 * 吞卡原因
	 *
	 * @return
	 */
	public String getReason();

	/**
	 * 机构编号
	 *
	 * @return
	 */
	public String getOrgCode();

	/**
	 * 吞卡状态
	 *
	 * @return
	 */
	public String getStatus();
}
