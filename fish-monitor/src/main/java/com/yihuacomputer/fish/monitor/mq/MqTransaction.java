package com.yihuacomputer.fish.monitor.mq;

import java.util.Date;

import com.yihuacomputer.fish.api.monitor.business.CardType;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.monitor.entity.business.Transaction;

public class MqTransaction {

    private String mqType = "TRANS";

    private long id;

    private String terminalId;

    private String transId;

    private String debitAccount;

    private String creditAccount;

    private String transCode;

    private double amt;

    private String currency;

    private Date dateTime;

    private int transDate;

    private String hostRet;

    private String localRet;

    private double tipFee;

    private CardType cardType;

    private String orgCode;

    private String orgName;

    private String errorText;

    private String localRetText;

    //交易耗时(毫秒)
    private long costTime ;

    public MqTransaction() {
    }

    public MqTransaction(ITransaction transaction) {
        Transaction trans = (Transaction) transaction;
        this.id = trans.getId();
        this.terminalId = trans.getTerminalId();
        setTransId(trans.getTransId());
        setDebitAccount(trans.getDebitAccount());
        setCreditAccount(trans.getCreditAccount());
        setTransCode(trans.getTransCode());
        setAmt(trans.getAmt());
        setCurrency(trans.getCurrency());
        this.dateTime = trans.getDateTime();
        this.transDate = trans.getTransDate();
        setHostRet(trans.getHostRet());
        setLocalRet(trans.getLocalRet());
        setTipFee(trans.getTipFee());

        setCardType(trans.getCardType());

        setOrgCode(trans.getOrgCode()== null?"":trans.getOrgCode());
        setOrgName(trans.getOrgName()== null?"":trans.getOrgName());
        setCostTime(trans.getCostTime());
    }

    public ITransaction makeTrans() {
        Transaction trans = new Transaction();
        trans.setId(this.id);
        trans.setTerminalId(this.terminalId);
        trans.setTransId(this.transId);
        trans.setDebitAccount(this.debitAccount);
        trans.setCreditAccount(this.creditAccount);
        trans.setTransCode(this.transCode);
        trans.setAmt(this.amt);
        trans.setCurrency(this.currency);
        trans.setDateTime(this.dateTime);
        trans.setTransDate(this.transDate);
        trans.setHostRet(this.hostRet);
        trans.setLocalRet(this.localRet);
        trans.setTipFee(this.tipFee);

        trans.setCardType(this.cardType);

        trans.setOrgCode(this.orgCode);
        trans.setOrgName(this.orgName);
        trans.setCostTime(this.costTime);

        return trans;
    }

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
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

    public String getMqType() {
        return mqType;
    }

    public void setMqType(String mqType) {
        this.mqType = mqType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getLocalRetText() {
		return localRetText;
	}

	public void setLocalRetText(String localRetText) {
		this.localRetText = localRetText;

	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

}
