package com.yihuacomputer.fish.api.report.trans;

/**
 * @author YiHua
 *
 */
public interface ISettlementCashInRpt {
    /**
     * @return
     */
    public String getOrgCode();

    /**
     * @param orgCode
     */
    public void setOrgCode(String orgCode);

    /**
     * @return
     */
    public String getOrgName();

    /**
     * @param orgName
     */
    public void setOrgName(String orgName);

    /**
     * @return
     */
    public String getDevTypeName();

    /**
     * @param devTypeName
     */
    public void setDevTypeName(String devTypeName);

    /**
     * @return
     */
    public String getSettleDate();

    /**
     * @param settleDate
     */
    public void setSettleDate(String settleDate);

    /**
     * @return
     */
    public String getCashDate();

    /**
     * @param cashDate
     */
    public void setCashDate(String cashDate);

    /**
     * @return
     */
    public double getCashAmt();

    /**
     * @param cashAmt
     */
    public void setCashAmt(double cashAmt);

    /**
     * @return
     */
    public double getLeftAmt();

    /**
     * @param leftAmt
     */
    public void setLeftAmt(double leftAmt);

    /**
     * @return
     */
    public String getUpCashDate();

    /**
     * @param upCashDate
     */
    public void setUpCashDate(String upCashDate);

    /**
     * @return
     */
    public double getUpCashAmt();

    /**
     * @param upCashAmt
     */
    public void setUpCashAmt(double upCashAmt);

    /**
     * @return
     */
    public String getTerminalId();

    /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);
}
