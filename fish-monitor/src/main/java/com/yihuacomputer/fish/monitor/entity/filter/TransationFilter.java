package com.yihuacomputer.fish.monitor.entity.filter;

import java.util.List;

import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;

public class TransationFilter implements ITransationFilter {

    private List<Long> subOrg;

    /** 设备号 */
    private String terminalId;

    /** 币种 */
    private String currency;

    /** 对方账号 */
    private String creditAccount;

    /** 客户账号 */
    private String debitAccount;

    /** 金额上限 */
    private double startAmt;

    /** 金额下限 */
    private double endAmt;

    /** 是否是黑名单卡 */
    private boolean isBlackCardList;

    /** 黑名单卡列表 */
    private List<String> blackCardList;

    /**
     * 交易状态(成功0,失败1)
     */
    private String tranStates;

    @Override
    public List<Long> getSubOrg() {
        return subOrg;
    }

    @Override
    public void setSubOrg(List<Long> subOrg) {
        this.subOrg = subOrg;
    }

    @Override
    public boolean filterTransaction(IDevice device, ITransaction trans) {
        boolean iscondition = false;

        // 设备信息为null
        if (device == null) {
            return iscondition;
        }

        // 上报的交易信息不属于此机构
        if (!subOrg.contains(Long.parseLong(device.getOrganization().getGuid()))) {
            return iscondition;
        }

        // 监控所有交易记录
        if (blackCardList == null) {
            iscondition = isFilter(trans);
            return iscondition;
        }

        // 监控黑名单卡交易记录
        if (isBlackCardList && blackCardList.contains(trans.getCreditAccount())
                || blackCardList.contains(trans.getDebitAccount())) {

            iscondition = isFilter(trans);

            return iscondition;
        }

        // 监控不属于黑名单卡交易记录
        if (!(blackCardList.contains(trans.getCreditAccount()) || blackCardList.contains(trans.getDebitAccount()))) {

            iscondition = isFilter(trans);

            return iscondition;
        }

        return iscondition;
    }

    /**
     * 是否符合条件
     *
     * @param trans
     * @return
     */
    private boolean isFilter(ITransaction trans) {

        // 检查设备
        boolean terminalIdBoo = StringUtils.isEmpty(terminalId) || terminalId.equals(trans.getTerminalId());
        if (!terminalIdBoo) {
            return false;
        }

        // 检查卡号
        boolean creditAccountBoo = StringUtils.isEmpty(creditAccount) || creditAccount.equals(trans.getCreditAccount());
        if (!creditAccountBoo) {
            return false;
        }

        // 检查转帐卡号
        boolean debitAccountBoo = StringUtils.isEmpty(debitAccount) || debitAccount.equals(trans.getDebitAccount());
        if (!debitAccountBoo) {
            return false;
        }

        // 检查金额
        boolean amtBoo = (Double.compare(startAmt, 0.0) == 0 && Double.compare(endAmt, 0.0) == 0) || (trans.getAmt() >= startAmt && trans.getAmt() <= endAmt);
        if (!amtBoo) {
            return false;
        }

        // 检查交易是否成功
        boolean statesBoo = StringUtils.isEmpty(tranStates) || ("0".equals(tranStates) ? "00".equals(trans.getHostRet())
                : !"00".equals(trans.getHostRet()));
        if (!statesBoo) {
            return false;
        }
        return true;
    }

    @Override
    public void setTerminalId(String terminalId) {
        if (terminalId == null || terminalId.isEmpty()) {
            this.terminalId = null;
        } else {
            this.terminalId = terminalId;
        }
    }

    @Override
    public void setCurrency(String currency) {
        if (currency == null || currency.isEmpty()) {
            this.currency = null;
        } else {
            this.currency = currency;
        }
    }

    @Override
    public void setCreditAccount(String creditAccount) {
        if (creditAccount == null || creditAccount.isEmpty()) {
            this.creditAccount = null;
        } else {
            this.creditAccount = creditAccount;
        }
    }

    @Override
    public void setDebitAccount(String debitAccount) {
        if (debitAccount == null || debitAccount.isEmpty()) {
            this.debitAccount = null;
        } else {
            this.debitAccount = debitAccount;
        }
    }

    @Override
    public void setStartAmt(double startAmt) {
        this.startAmt = startAmt;
    }

    @Override
    public void setEndAmt(double endAmt) {
        this.endAmt = endAmt;
    }

    @Override
    public String getTerminalId() {
        return terminalId;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getCreditAccount() {
        return creditAccount;
    }

    @Override
    public String getDebitAccount() {
        return debitAccount;
    }

    @Override
    public double getStartAmt() {
        return startAmt;
    }

    @Override
    public double getEndAmt() {
        return endAmt;
    }

    @Override
    public List<String> getBlackCardList() {
        return this.blackCardList;
    }

    @Override
    public boolean getIsBlackCardList() {
        return this.isBlackCardList;
    }

    @Override
    public void setIsBlackCardList(boolean isBlackCardList) {
        this.isBlackCardList = isBlackCardList;
    }

    @Override
    public void setBlackCardList(List<String> blackCardList) {
        this.blackCardList = blackCardList;
    }

    @Override
    public boolean filterCounterFeitMoney(IDevice device, List<CounterFeitMoneyForms> forms) {
        if (device == null) {
            return false;
        }
        for (CounterFeitMoneyForms trans : forms) {
            boolean iscondition = false;
            // 上报的交易信息不属于此机构
            if (!subOrg.contains(Long.parseLong(device.getOrganization().getGuid()))) {

                return iscondition;
            }
            // 监控所有交易记录
            if (blackCardList == null) {
                iscondition = isFilter(trans);

                return iscondition;
            }
            // 监控黑名单卡交易记录
            if (isBlackCardList && blackCardList.contains(trans.getCreditAccount())
                    || blackCardList.contains(trans.getDebitAccount())) {

                iscondition = isFilter(trans);

                return iscondition;
            }
            // 监控不属于黑名单卡交易记录
            if (!(blackCardList.contains(trans.getCreditAccount()) || blackCardList.contains(trans.getDebitAccount()))) {

                iscondition = isFilter(trans);

                return iscondition;
            }

            return iscondition;
        }
        return false;

    }

    /**
     * 是否符合条件
     *
     * @param trans
     * @return
     */
    private boolean isFilter(CounterFeitMoneyForms trans) {
        if ((terminalId == null || terminalId.equals(trans.getTermId()))
                && (creditAccount == null || creditAccount.equals(trans.getCreditAccount()))
                && (debitAccount == null || debitAccount.equals(trans.getDebitAccount()))
                && ((Double.compare(startAmt, 0.0) == 0 && Double.compare(endAmt, 0.0) == 0) || (trans.getAmt() >= startAmt && trans.getAmt() <= endAmt))) {

            return true;
        }

        return false;
    }

    @Override
    public String getTranStates() {
        return this.tranStates;
    }

    @Override
    public void setTranStates(String tranStates) {
        this.tranStates = StringUtils.isEmpty(tranStates) ? null : tranStates;
    }
}
