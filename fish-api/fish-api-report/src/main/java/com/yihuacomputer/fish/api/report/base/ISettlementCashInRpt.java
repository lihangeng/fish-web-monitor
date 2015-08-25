package com.yihuacomputer.fish.api.report.base;

public interface ISettlementCashInRpt {
    public String getOrgCode();

    public void setOrgCode(String orgCode);

    public String getOrgName();

    public void setOrgName(String orgName);

    public String getDevTypeName();

    public void setDevTypeName(String devTypeName);

    public String getSettleDate();

    public void setSettleDate(String settleDate);

    public String getCashDate();

    public void setCashDate(String cashDate);

    public double getCashAmt();

    public void setCashAmt(double cashAmt);

    public double getLeftAmt();

    public void setLeftAmt(double leftAmt);

    public String getUpCashDate();

    public void setUpCashDate(String upCashDate);

    public double getUpCashAmt();

    public void setUpCashAmt(double upCashAmt);

    public String getTerminalId();

    public void setTerminalId(String terminalId);
}
