package com.yihuacomputer.fish.api.atmlog;

/**
 * 交易信息
 * @author YiHua
 *
 */
public interface ITransCycle {
	
	/**
	 * 终端流水号
	 * @return
	 */
	public String getTerminalSerial();
	
	/**
	 * 主机流水号
	 * @return
	 */
	public String getHostserial();

	/**
	 * 交易类型
	 * @return
	 */
	public String getTransType();

	/**
	 * 交易金额
	 * @return
	 */
	public String getTransAmount();

	/**
	 * 主机返回
	 * @return
	 */
	public String getHostreturn();
	
	/**
	 * 钞箱列表
	 * @return
	 */
	public String getBoxList();
}
