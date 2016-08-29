package com.yihuacomputer.fish.api.report.trans;

public interface ISettlementRpt {
    
    /**
     * 所属机构
     * @return
     */
    public String getOrgName();
    
    /**
     * 设备号
     * @return
     */
    public String getTerminalId();
    
    /**
     * 清机日期
     * @return
     */
    public String getDate();
    
    /**
     * 存款金额
     * @return
     */
    public long getDepositAmt();
    
    /**
     * 取款金额
     * @return
     */
    public long getWithdrawalAmt();
    
    /**
     * 剩余金额
     * @return
     */
    public long getLeftAmt();

}
