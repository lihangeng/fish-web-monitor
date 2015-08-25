package com.yihuacomputer.fish.web.atm.format;


public class TransactionMsg {

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
	
	private String cardType;
	
	private String revFlag;
	
	private String cusName;
	
	private String cusPhone; 

	public TransactionMsg() {
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

	public String getCardType(){
		return cardType;
		
	}

	public void setCardType(String cardType){
		this.cardType = cardType;
	}

	public String getRevFlag(){
		return revFlag;
		
	}

	public void setRevflag(String revFlag){
		this.revFlag = revFlag;
	}
	
	public String getCusName(){
		return cusName;
		
	}
	public void setCusName(String cusName){
		this.cusName = cusName;
	}
	
	public String getCusPhone(){
		return cusPhone;
		
	}
	public void setCusPhone(String cusPhone){
		this.cusPhone = cusPhone;
	}
}
