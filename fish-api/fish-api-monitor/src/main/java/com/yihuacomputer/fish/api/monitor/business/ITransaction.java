package com.yihuacomputer.fish.api.monitor.business;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface ITransaction {

    /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * 获取设备号
     *
     * @return 设备号
     */
    public String getTerminalId();

    /**
     * 获取交易流水号
     *
     * @return 交易流水号
     */
    public String getTransId();

    /**
     * @param transId
     */
    public void setTransId(String transId);

    /**
     * 交易客户账号或者卡号
     *
     * @return 客户账号或者卡号
     */
    public String getDebitAccount();

    /**
     * @param debitAccount
     */
    public void setDebitAccount(String debitAccount);

    /**
     * 交易对方账号或者卡号
     *
     * @return 对方账号或者卡号
     */
    public String getCreditAccount();

    /**
     * @param creditAccount
     */
    public void setCreditAccount(String creditAccount);

    /**
     * 交易码
     *
     * @return 交易码
     */
    public String getTransCode();

    /**
     * @param transCode
     */
    public void setTransCode(String transCode);

    /**
     * 交易金额
     *
     * @return 交易金额
     */
    public double getAmt();

    /**
     * @param amt
     */
    public void setAmt(double amt);

    /**
     * 交易币种
     *
     * @return 交易币种
     */
    public String getCurrency();

    /**
     * @param currency
     */
    public void setCurrency(String currency);

    /**
     * 交易时间
     *
     * @return 交易时间
     */
    public Date getDateTime();

    /**
     * @param dateTime
     */
    public void setDateTime(Date dateTime);

    /**
     * 主机返回码
     *
     * @return 主机返回码
     */
    public String getHostRet();

    /**
     * @param hostRet
     */
    public void setHostRet(String hostRet);

    /**
     * ATMC本地代码
     *
     * @return ATMC本地代码
     */
    public String getLocalRet();

    /**
     * @param localRet
     */
    public void setLocalRet(String localRet);

    /**
     * ATM手续费、小费
     *
     * @return
     */
    public double getTipFee();

    /**
     * @param tipFee
     */
    public void setTipFee(double tipFee);

    /**
     * @param transDate
     */
    public void setTransDate(int transDate);

    /**
     * 交易卡归属（本行/他行）
     * @return
     */
    public CardType getCardType();

    /**
     * @param cardType
     */
    public void setCardType(CardType cardType);

    /**
     * 获取网点号
     *
     * @return
     */
    public String getOrgCode();

    /**
     * 设置网点号
     *
     * @param orgCode
     */
    public void setOrgCode(String orgCode);

    /**
     * 获取机构名称
     *
     * @return
     */
    public String getOrgName();

    /**
     * 设置机构名称
     *
     * @param orgName
     */
    public void setOrgName(String orgName);

    /**
     * 交易耗时(单位：毫秒)
     *
     * @return
     */
    public long getCostTime();

    /**
     * 设置交易耗时(单位：毫秒)
     *
     * @param costTime
     */
    public void setCostTime(long costTime);
}
