package com.yihuacomputer.fish.web.atm.format;

import java.util.List;

import com.yihuacomputer.fish.monitor.entity.business.NoteItem;

/**
 * @author YiHua
 *
 */
public class CounterFeitMoneyMsg {

	private String termId;

	private String transId;

	private String debitAccount;

	private String creditAccount;

	private String transCode;

	private double amt;

	private String currency;

	private long dateTime;

	private String hostRet;

	private String localRet;

	private double tipFee;

	private int transDate;
	
	//假币疑问币数量
	private int counterFeitMoney;
	
	private List<NoteItem> noteItem;

	public CounterFeitMoneyMsg() {
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

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
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

	public int getTransDate() {
		return transDate;
	}

	public void setTransDate(int transDate) {
		this.transDate = transDate;
	}

	public int getCounterFeitMoney()
    {
        return counterFeitMoney;
    }

    public void setCounterFeitMoney(int counterFeitMoney)
    {
        this.counterFeitMoney = counterFeitMoney;
    }
    
    public List<NoteItem> getNoteItem() {
        return noteItem;
    }
    
    public void setNoteItem(List<NoteItem> noteItem) {
        this.noteItem = noteItem;
    }

}

