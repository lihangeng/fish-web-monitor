package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
/**
 * @author YiHua
 *
 */
public class CounterFeitMoneyReport {
	
	//交易流水号
	private String transId;
	//交易卡号
	private String creditAccount;
	//deviceCode
	private String termId;
	//对方账号
	private String debitAccount;
	//交易类型
	private String transCode;
	//交易金额
	private double amt;
	//交易币种
	private String currency;
	//交易时间
	private String dateTime;
	//交易结果
	private String hostRet;
	//设备响应码
	private String localRet;
	//假币疑问币数量
	private int counterFeitMoney;
	private int serial;
	private String noteCode;
	private String orgName;
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getDebitAccount() {
		return debitAccount;
	}
	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getHostRet() {
		return hostRet;
	}
	public void setHostRet(String hostRet) {
		this.hostRet = hostRet;
	}
	public String getLocalRet() {
		return localRet;
	}
	public void setLocalRet(String localRet) {
		this.localRet = localRet;
	}
	public int getCounterFeitMoney()
    {
        return counterFeitMoney;
    }

    public void setCounterFeitMoney(int counterFeitMoney)
    {
        this.counterFeitMoney = counterFeitMoney;
    }

	
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		
	}

	public String getNoteCode() {
		return noteCode;
	}

	public void setNoteCode(String noteCode) {
		this.noteCode = noteCode;
		
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
		
	}
	/**
	 * @param forms
	 */
	public void setCounterFeitMoney(CounterFeitMoneyForms forms){
		this.setDebitAccount(forms.getDebitAccount());
		this.setCreditAccount(forms.getCreditAccount());
		this.setTermId(forms.getTermId());
		this.setLocalRet(forms.getLocalRet());
		this.setCurrency(forms.getCurrency());
		this.setDateTime(forms.getDateTime());
		this.setTransId(forms.getTransId());
		this.setAmt(forms.getAmt());
		this.setHostRet(forms.getHostRet());
		this.setTransCode(forms.getTransCode());
		this.setCounterFeitMoney(forms.getCounterFeitMoney());
		this.setSerial(forms.getSerial());
		this.setNoteCode(forms.getNoteCode());
		this.setOrgName(forms.getOrgName());
	}
	
	/**
	 * @param forms
	 */
	public CounterFeitMoneyReport(CounterFeitMoneyForms forms) {
		this.setDebitAccount(forms.getDebitAccount());
		this.setCreditAccount(forms.getCreditAccount());
		this.setTermId(forms.getTermId());
		this.setLocalRet(forms.getLocalRet());
		this.setCurrency(forms.getCurrency());
		this.setDateTime(forms.getDateTime());
		this.setTransId(forms.getTransId());
		this.setAmt(forms.getAmt());
		this.setHostRet(forms.getHostRet());
		this.setTransCode(forms.getTransCode());
		this.setCounterFeitMoney(forms.getCounterFeitMoney());
		this.setSerial(forms.getSerial());
		this.setNoteCode(forms.getNoteCode());
		this.setOrgName(forms.getOrgName());
	}
}