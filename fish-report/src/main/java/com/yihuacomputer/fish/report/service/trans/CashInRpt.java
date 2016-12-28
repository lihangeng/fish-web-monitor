package com.yihuacomputer.fish.report.service.trans;


import com.yihuacomputer.fish.api.report.trans.ICashInRpt;

/**
 * @author YiHua
 *
 */
public class CashInRpt implements ICashInRpt{

    private String orgName;
    
    private String terminalId;
    
    private String date;
    
    private long cashInAmt;

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setCashInAmt(long cashInAmt) {
        this.cashInAmt = cashInAmt;
    }

    public void setDate(String date) {
        this.date = date;
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
    public long getCashInAmt() {
        return this.cashInAmt;
    }

    @Override
    public String getDate() {
        return this.date;
    }
    
   

}
