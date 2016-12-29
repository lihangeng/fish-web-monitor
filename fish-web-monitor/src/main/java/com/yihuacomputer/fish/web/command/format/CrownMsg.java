package com.yihuacomputer.fish.web.command.format;

/**
 * @author YiHua
 *
 */
public class CrownMsg {
	private String terminalId;
	private String dateTime;
	private String transId;
	private String transAccount;
	private String crownId;
	private String currency;
	private String flag = "1";// 用来标识该冠字号信息来之数据库还是远程C端（0表示来自数据库，1表示来自C端）

	private String version;
	private String transType;
	private String state;

	public CrownMsg() {
	}

	/**
	 * @param terminalId
	 * @param dateTime
	 * @param transId
	 * @param transAccount
	 * @param crownId
	 * @param currency
	 */
	public CrownMsg(String terminalId, String dateTime, String transId, String transAccount, String crownId,
			String currency) {
		this.terminalId = terminalId;
		this.dateTime = dateTime;
		this.transId = transId;
		this.transAccount = transAccount;
		this.crownId = crownId;
		this.currency = currency;
		this.flag = "1";
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransAccount() {
		return transAccount;
	}

	public void setTransAccount(String transAccount) {
		this.transAccount = transAccount;
	}

	public String getCrownId() {
		return crownId;
	}

	public void setCrownId(String crownId) {
		this.crownId = crownId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
