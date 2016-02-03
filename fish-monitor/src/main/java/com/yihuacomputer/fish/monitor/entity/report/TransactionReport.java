package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;

public class TransactionReport {

    private String transId;// 交易流水号
    private String creditAccount;// 交易卡号
    private String termId;// deviceCode
    private String debitAccount;// 对方账号
    private String transCode;// 交易类型
    private double amt;// 交易金额
    private String currency;// 交易币种
    private String dateTime;// 交易时间
    private String hostRet;// 交易结果
    private String localRet;// 设备响应码

    /**
     * 网点
     */
    private String orgCode;

    /**
     * 网点名称
     */
    private String orgName;

    /**
     * 耗时
     */
    private long costTime;
    
    /**
     * 卡类型
     */
    private String cardType;

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

    public void setTransaction(ITransaction trans) {
        this.setDebitAccount(trans.getDebitAccount());
        this.setCreditAccount(trans.getCreditAccount());
        this.setTermId(trans.getTerminalId());
        this.setLocalRet(trans.getLocalRet());
        this.setCurrency(trans.getCurrency());
        this.setDateTime(DateUtils.getTimestamp(trans.getDateTime()));
        this.setTransId(trans.getTransId());
        this.setAmt(trans.getAmt());
        this.setHostRet(trans.getHostRet());
        this.setTransCode(trans.getTransCode());

        this.setOrgCode(trans.getOrgCode());
        this.setOrgName(trans.getOrgName());

        this.setCostTime(trans.getCostTime());
        this.setCardType(String.valueOf(trans.getCardType()));
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
