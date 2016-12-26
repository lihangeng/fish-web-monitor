package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;

/**
 * 交易监控条件
 *
 * @author YiHua
 *
 */
public interface ITransationFilter {

    /**
     * @return
     */
    public List<Long> getSubOrg();

    /**
     * @param subOrg
     */
    public void setSubOrg(List<Long> subOrg);

    /**
     * @param device
     * @param trans
     * @return
     */
    public boolean filterTransaction(IDevice device, ITransaction trans);

    /**
     * @return
     */
    public String getTerminalId();

    /**
     * @return
     */
    public String getCurrency();

    /**
     * @return
     */
    public String getCreditAccount();

    /**
     * @return
     */
    public String getDebitAccount();

    /**
     * @return
     */
    public double getStartAmt();

    /**
     * @return
     */
    public double getEndAmt();

    /**
     * @return
     */
    public List<String> getBlackCardList();

    /**
     * @return
     */
    public boolean getIsBlackCardList();

    /**
     * @param isBlackCardList
     */
    public void setIsBlackCardList(boolean isBlackCardList);

    /**
     * @param blackCardList
     */
    public void setBlackCardList(List<String> blackCardList);

    /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * @param currency
     */
    public void setCurrency(String currency);

    /**
     * @param creditAccount
     */
    public void setCreditAccount(String creditAccount);

    /**
     * @param debitAccount
     */
    public void setDebitAccount(String debitAccount);

    /**
     * @param startAmt
     */
    public void setStartAmt(double startAmt);

    /**
     * @param endAmt
     */
    public void setEndAmt(double endAmt);

    /**
     * @param device
     * @param forms
     * @return
     */
    public boolean filterCounterFeitMoney(IDevice device, List<CounterFeitMoneyForms> forms);

    /**
     * 获取交易状态(成功0,失败1)
     *
     * @return
     */
    public String getTranStates();

    /**
     * 设置交易状态(成功0,失败1)
     *
     * @param tranStates
     */
    public void setTranStates(String tranStates);

}
