package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;


import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.INoteItem;

/**
 * @author YiHua
 *
 */
public class CounterFeitMoneyForms{
    
	private String termId;

	private String transId;

	private String debitAccount;

	private String creditAccount;

	private String transCode;

	private double amt;

	private String currency;

	private String dateTime;

	private String hostRet;

	private String localRet;

	private double tipFee;	

	private int serial;

	private String noteCode;
	
	private int counterFeitMoney;
	
	private String orgName;
	
	public CounterFeitMoneyForms() {
	}

	/**
	 * @param counterFeitMoney
	 */
	public CounterFeitMoneyForms(ICounterFeitMoney counterFeitMoney) {
		List<INoteItem> noteResultList = counterFeitMoney.getNoteItem();
		
		for(INoteItem noteItem : noteResultList){
			setTermId(counterFeitMoney.getTerminalId());
			setTransId(counterFeitMoney.getTransId());
			setDebitAccount(counterFeitMoney.getDebitAccount());
			setCreditAccount(counterFeitMoney.getCreditAccount());
			setTransCode(counterFeitMoney.getTransCode());
			setAmt(counterFeitMoney.getAmt());
			setCurrency(counterFeitMoney.getCurrency());
			setDateTime(DateUtils.getTimestamp(counterFeitMoney.getDateTime()));
			setHostRet(counterFeitMoney.getHostRet());
			setLocalRet(counterFeitMoney.getLocalRet());
			setTipFee(counterFeitMoney.getTipFee());
			setCounterFeitMoney(counterFeitMoney.getCounterFeitMoney());
			setSerial(noteItem.getSerial());
			setNoteCode(noteItem.getNoteCode());
		}
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
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

	public double getTipFee() {
		return tipFee;
	}

	public void setTipFee(double tipFee) {
		this.tipFee = tipFee;
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

	public int getCounterFeitMoney() {
		return counterFeitMoney;
	}

	public void setCounterFeitMoney(int counterFeitMoney) {
		this.counterFeitMoney = counterFeitMoney;
		
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
		
	}
}