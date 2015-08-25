package com.yihuacomputer.fish.report.base;

import com.yihuacomputer.fish.api.report.base.ITransCountRpt;

public class TransCountRpt implements ITransCountRpt {

    private String terminalId;

    private String orgName;

    private String transType;

    private double transCount;

    private String countName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public String getCountName() {
        return countName;
    }

    @Override
    public double getTransCount() {
        return transCount;
    }

    public void setTransCount(double transCount) {
        this.transCount = transCount;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    @Override
    public String getTerminalId() {
        return this.terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
