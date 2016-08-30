package com.yihuacomputer.fish.api.report.trans;

/**
 * 加钞情况模型
 * @author YiHua
 *
 */
public interface ICashInRpt {

	/**
	 * 所属机构
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * 加钞时间
	 * @return
	 */
	public String getDate();
	
//	/**
//	 * 加钞张数
//	 * @return
//	 */
//	public long getCashInCount();
	
	/**
	 * 加钞金额
	 * @return
	 */
	public long getCashInAmt();
}
