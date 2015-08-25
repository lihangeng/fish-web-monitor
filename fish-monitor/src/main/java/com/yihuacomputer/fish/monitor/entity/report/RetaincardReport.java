package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;

public class RetaincardReport {

	private long id;
	/**
	 * 设备号
	 */
	private String terminalId;
	/**
	 * 卡号
	 */
	private String accountNo;
	/**
	 * 吞卡时间
	 */
	private String cardRetainTime;
	/**
	 * 发卡银行
	 */
	private String cardDistributionBank;
	/**
	 * 吞卡原因
	 */
	private String reason;

	/**
	 * 所属机构
	 */
	private String subsidiaryorganId;

	private String subsidiaryorganName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCardRetainTime() {
		return cardRetainTime;
	}

	public void setCardRetainTime(String cardRetainTime) {
		this.cardRetainTime = cardRetainTime;
	}

	public String getCardDistributionBank() {
		return cardDistributionBank;
	}

	public void setCardDistributionBank(String cardDistributionBank) {
		this.cardDistributionBank = cardDistributionBank;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSubsidiaryorganId() {
		return subsidiaryorganId;
	}

	public void setSubsidiaryorganId(String subsidiaryorganId) {
		this.subsidiaryorganId = subsidiaryorganId;
	}

	public String getSubsidiaryorganName() {
		return subsidiaryorganName;
	}

	public void setSubsidiaryorganName(String subsidiaryorganName) {
		this.subsidiaryorganName = subsidiaryorganName;
	}


	public void setRetaincard(IRetaincard retaincard) {
		setTerminalId(retaincard.getTerminalId());
		setAccountNo(retaincard.getAccountNo());
		setCardRetainTime(DateUtils.get(retaincard.getCardRetainTime(), "yyyy-MM-dd HH:mm:ss"));
		setCardDistributionBank(retaincard.getCardDistributionBank());
		setReason(retaincard.getReason());

	}
}
