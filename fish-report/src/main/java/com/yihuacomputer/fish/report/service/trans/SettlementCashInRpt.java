package com.yihuacomputer.fish.report.service.trans;

import com.yihuacomputer.fish.api.report.trans.ISettlementCashInRpt;

public class SettlementCashInRpt implements ISettlementCashInRpt {
    private String orgCode;
    
    private String orgName;
    
    private String devTypeName;
    
    private String settleDate;
    
    private String upCashDate;
    
    private double upCashAmt;
    
    private String cashDate;
    
    private double cashAmt;
    
    private double leftAmt;
    
    private String terminalId;
    
    @Override
    public String getTerminalId() {
        return terminalId;
    }

    @Override
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String getUpCashDate() {
        return upCashDate;
    }

    @Override
    public void setUpCashDate(String upCashDate) {
        this.upCashDate = upCashDate;
    }

    @Override
    public double getUpCashAmt() {
        return upCashAmt;
    }

    @Override
    public void setUpCashAmt(double upCashAmt) {
        this.upCashAmt = upCashAmt;
    }

    @Override
    public String getOrgCode() {
        return orgCode;
    }

    @Override
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String getOrgName() {
        return orgName;
    }

    @Override
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String getDevTypeName() {
        return devTypeName;
    }

    @Override
    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }

    @Override
    public String getSettleDate() {
        return settleDate;
    }

    @Override
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    @Override
    public String getCashDate() {
        return cashDate;
    }

    @Override
    public void setCashDate(String cashDate) {
        this.cashDate = cashDate;
    }

    @Override
    public double getCashAmt() {
        return cashAmt;
    }

    @Override
    public void setCashAmt(double cashAmt) {
        this.cashAmt = cashAmt;
    }

    @Override
    public double getLeftAmt() {
        return leftAmt;
    }

    @Override
    public void setLeftAmt(double leftAmt) {
        this.leftAmt = leftAmt;
    }
}
