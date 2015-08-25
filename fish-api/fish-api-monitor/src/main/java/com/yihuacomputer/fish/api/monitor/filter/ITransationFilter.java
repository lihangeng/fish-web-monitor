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

    public List<Long> getSubOrg();

    public void setSubOrg(List<Long> subOrg);

    public boolean filterTransaction(IDevice device, ITransaction trans);

    public String getTerminalId();

    public String getCurrency();

    public String getCreditAccount();

    public String getDebitAccount();

    public double getStartAmt();

    public double getEndAmt();

    public List<String> getBlackCardList();

    public boolean getIsBlackCardList();

    public void setIsBlackCardList(boolean isBlackCardList);

    public void setBlackCardList(List<String> blackCardList);

    public void setTerminalId(String terminalId);

    public void setCurrency(String currency);

    public void setCreditAccount(String creditAccount);

    public void setDebitAccount(String debitAccount);

    public void setStartAmt(double startAmt);

    public void setEndAmt(double endAmt);

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
