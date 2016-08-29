package com.yihuacomputer.fish.report.service.trans;

import com.yihuacomputer.fish.api.report.trans.ISettlementRpt;

public class SettlementRpt implements ISettlementRpt{
    
    private String orgName;
    
    private String terminalId;
    
    private String date;
    
    private long depositAmt;
    
    private long withdrawalAmt;
    
    private long leftAmt;

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepositAmt(long depositAmt) {
        this.depositAmt = depositAmt;
    }

    public void setWithdrawalAmt(long withdrawalAmt) {
        this.withdrawalAmt = withdrawalAmt;
    }

    public void setLeftAmt(long leftAmt) {
        this.leftAmt = leftAmt;
    }

    @Override
    public String getOrgName() {
        return this.orgName;
    }

    @Override
    public String getTerminalId() {
        return this.terminalId;
    }

    @Override
    public long getDepositAmt() {
        return this.depositAmt;
    }

    @Override
    public long getWithdrawalAmt() {
        return this.withdrawalAmt;
    }

    @Override
    public long getLeftAmt() {
        return this.leftAmt;
    }

    @Override
    public String getDate() {
        return this.date;
    }

}
