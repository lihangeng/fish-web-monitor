package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;
/**
 * 清机结账信息
 * @author YiHua
 *
 */
public interface ISettlement {
	/**
	 * @return
	 */
	public String getUuId();

	/**
	 * @param date
	 */
	public void setDate(String date);
	
	/**
	 * @return
	 */
	public String getDate();
	
	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	
	/**
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * @return
	 */
	public long getLeftAmt();

	/**
	 * @return
	 */
	public List<IBoxSettleDetail> getBoxDetail();

	/**
	 * @return
	 */
	public long getDeposit();

	/**
	 * @return
	 */
	public long getDepositAmt();

	/**
	 * @return
	 */
	public long getWithdrawal();

	/**
	 * @return
	 */
	public long getWithdrawalAmt();

	/**
	 * @param uuId
	 */
	public void setUuId(String uuId);

	/**
	 * @param leftAmt
	 */
	public void setLeftAmt(long leftAmt);

	/**
	 * @param boxDetail
	 */
	public void setBoxDetail(List<IBoxSettleDetail> boxDetail);

	/**
	 * @param deposit
	 */
	public void setDeposit(long deposit);

	/**
	 * @param depositAmt
	 */
	public void setDepositAmt(long depositAmt);

	/**
	 * @param withdrawal
	 */
	public void setWithdrawal(long withdrawal);

	/**
	 * @param withdrawalAmt
	 */
	public void setWithdrawalAmt(long withdrawalAmt);

	/**
	 * @return
	 */
	public long getTransactionAmt();

	/**
	 * @param transactionAmt
	 */
	public void setTransactionAmt(long transactionAmt);

	/**
	 * @return
	 */
	public long getTransaction();

	/**
	 * @param transaction
	 */
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
