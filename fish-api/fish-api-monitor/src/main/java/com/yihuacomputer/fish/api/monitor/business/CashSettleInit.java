package com.yihuacomputer.fish.api.monitor.business;

public class CashSettleInit {
	private String date;
	private long initAmt;
	private long leftAmt;
	private long deposit;
	private long depositAmt;
	private long withdrawal;
	private long withdrawalAmt;
	/**
	 * 清机或加钞信息
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 清机或加钞信息
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 加钞金额
	 * @return
	 */
	public long getInitAmt() {
		return initAmt;
	}
	/**
	 *  加钞金额
	 * @param initAmt
	 */
	public void setInitAmt(long initAmt) {
		this.initAmt = initAmt;
	}
	/**
	 * 清机金额
	 * @return
	 */
	public long getLeftAmt() {
		return leftAmt;
	}
	/**
	 * 清机金额
	 * @param leftAmt
	 */
	public void setLeftAmt(long leftAmt) {
		this.leftAmt = leftAmt;
	}
	/**
	 * 存款次数
	 * @return
	 */
	public long getDeposit() {
		return deposit;
	}
	/**
	 * 存款次数
	 * @param deposit
	 */
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	/**
	 * 存款金额
	 * @return
	 */
	public long getDepositAmt() {
		return depositAmt;
	}
	/**
	 * 存款金额
	 * @param depositAmt
	 */
	public void setDepositAmt(long depositAmt) {
		this.depositAmt = depositAmt;
	}
	/**
	 * 取款次数
	 * @return
	 */
	public long getWithdrawal() {
		return withdrawal;
	}
	/**
	 * 取款次数
	 * @param withdrawal
	 */
	public void setWithdrawal(long withdrawal) {
		this.withdrawal = withdrawal;
	}
	/**
	 * 取款金额
	 * @return
	 */
	public long getWithdrawalAmt() {
		return withdrawalAmt;
	}
	/**
	 * 取款金额
	 * @param withdrawalAmt
	 */
	public void setWithdrawalAmt(long withdrawalAmt) {
		this.withdrawalAmt = withdrawalAmt;
	}
}
