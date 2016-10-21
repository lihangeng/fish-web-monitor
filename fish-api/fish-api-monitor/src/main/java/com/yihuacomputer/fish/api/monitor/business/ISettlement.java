package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;
/**
 * 清机结账信息
 * @author YiHua
 *
 */
public interface ISettlement {
	public String getUuId();

	public void setDate(String date);
	
	public String getDate();
	
	public void setTerminalId(String terminalId);
	
	public String getTerminalId();
	
	public long getLeftAmt();

	public List<IBoxSettleDetail> getBoxDetail();

	public long getDeposit();

	public long getDepositAmt();

	public long getWithdrawal();

	public long getWithdrawalAmt();

	public void setUuId(String uuId);

	public void setLeftAmt(long leftAmt);

	public void setBoxDetail(List<IBoxSettleDetail> boxDetail);

	public void setDeposit(long deposit);

	public void setDepositAmt(long depositAmt);

	public void setWithdrawal(long withdrawal);

	public void setWithdrawalAmt(long withdrawalAmt);

	public long getTransactionAmt();

	public void setTransactionAmt(long transactionAmt);

	public long getTransaction();

	public void setTransaction(long transaction);

	/**
	 * 转化为日期yyyymmdd
	 * @return
	 */
	public int getDates();
	/**
	 * 转化为日期yyyymmdd
	 * @param dates
	 */
	public void setDates(int dates);
}
