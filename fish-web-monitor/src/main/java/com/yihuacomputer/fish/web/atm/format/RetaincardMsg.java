package com.yihuacomputer.fish.web.atm.format;
/**
 * C端应用吞卡信息
 * @author YiHua
 *
 */
public class RetaincardMsg {

	private String termId;
	private String accountNo;
	private String reason;
	private String retainTime;
	public String getTermId(){
		return this.termId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getRetainTime() {
		return retainTime;
	}
	public void setRetainTime(String retainTime) {
		this.retainTime = retainTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	
}
