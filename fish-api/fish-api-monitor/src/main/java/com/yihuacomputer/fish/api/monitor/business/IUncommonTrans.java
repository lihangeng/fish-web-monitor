package com.yihuacomputer.fish.api.monitor.business;

import java.util.Date;

public interface IUncommonTrans {
	
	/**
	 * 获取交易id
	 * @return id
	 */
	public long getId();
	
	public void setId(long id);
	
	public void setTerminalId(String terminalId);

	/**
	 * 获取设备号
	 * @return 设备号
	 */
	public String getTerminalId();

	/**
	 * 获取交易流水号
	 * @return 交易流水号
	 */
	public String getTransId();

	public void setTransId(String transId);

	/**
	 * 交易客户账号或者卡号
	 * @return 客户账号或者卡号
	 */
	public String getDebitAccount();

	public void setDebitAccount(String debitAccount);

	/**
	 * 交易对方账号或者卡号
	 * @return 对方账号或者卡号
	 */
	public String getCreditAccount();

	public void setCreditAccount(String creditAccount);

	/**
	 * 交易码
	 * @return 交易码
	 */
	public String getTransCode();

	public void setTransCode(String transCode);

	/**
	 * 交易金额
	 * @return 交易金额
	 */
	public double getAmt();

	public void setAmt(double amt);

	/**
	 * 交易币种
	 * @return 交易币种
	 */
	public String getCurrency();

	public void setCurrency(String currency);

	/**
	 * 交易时间
	 * @return 交易时间
	 */
	public Date getDateTime();

	public void setDateTime(Date dateTime);

	/**
	 * 主机返回码
	 * @return 主机返回码
	 */
	public String getHostRet();

	public void setHostRet(String hostRet);

	/**
	 * ATMC本地代码
	 * @return ATMC本地代码
	 */
	public String getLocalRet();

	public void setLocalRet(String localRet);

	/**
	 * ATM手续费、小费
	 * @return
	 */
	public double getTipFee();

	public void setTipFee(double tipFee);

	public void setTransDate(int transDate);
	public int getTransDate();
	
	/**
	 * 获取客户姓名
	 * @return 姓名
	 */
	public String getCustName();

	public void setCustName(String custName);
	
	/**
	 * 获取客户手机号
	 * @return 手机号
	 */
	public String getCustPhone();

	public void setPhone(String phone);
	
	/**
	 * 获取后端处理结果
	 * @return 手机号
	 */
	public String getResultFromV();

	public void setResultFromV(String resultFromV);
	

	/**
	 * 冲正标志(青海农信)
	 *
	 * @return 0 非冲1 冲正2 被冲正
	 */
	public String getRevflag();

	void setRevflag(String revflag);
	
	/**
	 * 得到卡类型.
	 *
	 * @return CardType
	 */
	public String getCardType();

	public void setCardType(String cardType);
	
	/**
	 * 获取登记处理意见
	 * @return 登记处理意见
	 */
	public String getSuggest();

	public void setSuggest(String suggest);
}