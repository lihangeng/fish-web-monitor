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
    
    
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getUpCashDate() {
        return upCashDate;
    }

    public void setUpCashDate(String upCashDate) {
        this.upCashDate = upCashDate;
    }

    public double getUpCashAmt() {
        return upCashAmt;
    }

    public void setUpCashAmt(double upCashAmt) {
        this.upCashAmt = upCashAmt;
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

    public String getDevTypeName() {
        return devTypeName;
    }

    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getCashDate() {
        return cashDate;
    }

    public void setCashDate(String cashDate) {
        this.cashDate = cashDate;
    }

    public double getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(double cashAmt) {
        this.cashAmt = cashAmt;
    }

    public double getLeftAmt() {
        return leftAmt;
    }

    public void setLeftAmt(double leftAmt) {
        this.leftAmt = leftAmt;
    }
}
