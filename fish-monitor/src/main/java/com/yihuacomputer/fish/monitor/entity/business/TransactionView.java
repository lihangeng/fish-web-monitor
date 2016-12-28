package com.yihuacomputer.fish.monitor.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.business.CardType;
import com.yihuacomputer.fish.api.monitor.business.ITransactionView;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "ATMC_TRANSACTION_VIEW")
public class TransactionView implements ITransactionView {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "TERMINAL_ID", length = 20, nullable = false)
    private String terminalId;

    @Column(name = "TRANS_ID", length = 20, nullable = false)
    private String transId;

    @Column(name = "DEBIT_ACCOUNT", length = 20)
    private String debitAccount;

    @Column(name = "CREDIT_ACCOUNT", length = 20)
    private String creditAccount;

    @Column(name = "TRANS_CODE", length = 20)
    private String transCode;

    @Column(name = "AMT")
    private double amt;

    @Column(name = "CURRENCY", length = 5)
    private String currency;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_TIME", length = 20)
    private Date dateTime;

    @Column(name = "TRANS_DATE", length = 12)
    private int transDate;

    @Column(name = "HOST_RET", length = 10)
    private String hostRet;

    @Column(name = "LOCAL_RET", length = 10)
    private String localRet;

    @Column(name = "TIP_FEE")
    private double tipFee;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CARD_TYPE")
    private CardType cardType;

    @Column(name = "COST_TIME")
    private long costTime;

    /**
     * 网点编号
     */
    @Transient
    private String orgCode;

    /**
     * 网点名称
     */
    @Transient
    private String orgName;

    @Override
    public String getTerminalId() {
        return terminalId;
    }

    @Override
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String getTransId() {
        return transId;
    }

    @Override
    public void setTransId(String transId) {
        this.transId = transId;
    }

    @Override
    public String getDebitAccount() {
        return debitAccount;
    }

    @Override
    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    @Override
    public String getCreditAccount() {
        return creditAccount;
    }

    @Override
    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    @Override
    public String getTransCode() {
        return transCode;
    }

    @Override
    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    @Override
    public double getAmt() {
        return amt;
    }

    @Override
    public void setAmt(double amt) {
        this.amt = amt;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public Date getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getHostRet() {
        return hostRet;
    }

    @Override
    public void setHostRet(String hostRet) {
        this.hostRet = hostRet;
    }

    @Override
    public String getLocalRet() {
        return localRet;
    }

    @Override
    public void setLocalRet(String localRet) {
        this.localRet = localRet;
    }

    @Override
    public double getTipFee() {
        return tipFee;
    }

    @Override
    public void setTipFee(double tipFee) {
        this.tipFee = tipFee;
    }

    public int getTransDate() {
        return transDate;
    }

    @Override
    public void setTransDate(int transDate) {
        this.transDate = transDate;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String getOrgCode() {
        return this.orgCode;
    }

    @Override
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String getOrgName() {
        return this.orgName;
    }

    @Override
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public long getCostTime() {
        return this.costTime;
    }

    @Override
    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
