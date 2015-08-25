package com.yihuacomputer.fish.report.base;

import com.yihuacomputer.fish.api.report.base.ITransResultCountRpt;

public class TransResultCountRpt implements ITransResultCountRpt{
    
    private String orgName;
    
    private String result;
    
    private String countName;
    
    private double transCount;

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public void setTransCount(double transCount) {
        this.transCount = transCount;
    }

    @Override
    public String getOrgName() {
        return this.orgName;
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public String getCountName() {
        return this.countName;
    }

    @Override
    public double getTransCount() {
        return this.transCount;
    }

}
