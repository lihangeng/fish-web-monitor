package com.yihuacomputer.fish.web.atm.format;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.IBoxSettleDetail;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.monitor.entity.business.BoxSettleDetail;

/**
 * C端应用清机结账信息
 *
 * @author YiHua
 *
 */
public class SettlementMsg {

    private String termId;

    private String uuId;

    private String date;

    private long leftAmt;

    private List<BoxSettleDetail> boxDetail;

    private long deposit;

    private long depositAmt;

    private long withdrawal;

    private long withdrawalAmt;

    private long transaction;

    private long transactionAmt;

    public SettlementMsg() {
    }

    public SettlementMsg(ISettlement settlement) {
        setTermId(settlement.getTerminalId());
        setUuId(settlement.getUuId());
        setDate(settlement.getDate());
        setLeftAmt(settlement.getLeftAmt());
        List<BoxSettleDetail> detailList = new ArrayList<BoxSettleDetail>();
        long counter = 0;
        for (IBoxSettleDetail iDetail : settlement.getBoxDetail()) {
        	BoxSettleDetail bsd = new BoxSettleDetail(iDetail);
        	bsd.setId(++counter);
            detailList.add(bsd);
        }
        setBoxDetail(detailList);
        setDeposit(settlement.getDeposit());
        setDepositAmt(settlement.getDepositAmt());
        setWithdrawal(settlement.getWithdrawal());
        setWithdrawalAmt(settlement.getWithdrawalAmt());
        setTransaction(settlement.getTransaction());
        setTransactionAmt(settlement.getTransactionAmt());
    }

    public static List<SettlementMsg> convert(List<ISettlement> settlementList) {
        List<SettlementMsg> result = new ArrayList<SettlementMsg>();
        for (ISettlement iSettlement : settlementList) {
            result.add(new SettlementMsg(iSettlement));
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getTermId() {
        return this.termId;
    }

    public String getUuId() {
        return uuId;
    }

    public long getLeftAmt() {
        return leftAmt;
    }

    public List<BoxSettleDetail> getBoxDetail() {
        return boxDetail;
    }

    public long getDeposit() {
        return deposit;
    }

    public long getDepositAmt() {
        return depositAmt;
    }

    public long getWithdrawal() {
        return withdrawal;
    }

    public long getWithdrawalAmt() {
        return withdrawalAmt;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public void setLeftAmt(long leftAmt) {
        this.leftAmt = leftAmt;
    }

    public void setBoxDetail(List<BoxSettleDetail> boxDetail) {
        this.boxDetail = boxDetail;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public void setDepositAmt(long depositAmt) {
        this.depositAmt = depositAmt;
    }

    public void setWithdrawal(long withdrawal) {
        this.withdrawal = withdrawal;
    }

    public void setWithdrawalAmt(long withdrawalAmt) {
        this.withdrawalAmt = withdrawalAmt;
    }

    public long getTransaction() {
        return transaction;
    }

    public void setTransaction(long transaction) {
        this.transaction = transaction;
    }

    public long getTransactionAmt() {
        return transactionAmt;
    }

    public void setTransactionAmt(long transactionAmt) {
        this.transactionAmt = transactionAmt;
    }
}
