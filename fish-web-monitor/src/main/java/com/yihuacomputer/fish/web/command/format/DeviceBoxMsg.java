package com.yihuacomputer.fish.web.command.format;

import java.util.List;

public class DeviceBoxMsg {

	private String termId;

	private List<CashBoxDetail> boxList;

	/**
	 * 装钞总金额.
	 */
	private int initAmount;

	/**
	 * 剩钞金额.
	 */
	private int amount;

	/**
	 * 出钞总金额.
	 */
	private int dispenseAmount;

	/**
	 * 废钞金额.
	 */
	private int rejectAmount;

	/**
	 * 钞票回收次数.
	 */
	private int retractCount;

	/**
	 * 最小取款金额
	 */
	private int minAmount;

	private String ret;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public List<CashBoxDetail> getBoxList() {
		return boxList;
	}

	public void setBoxList(List<CashBoxDetail> boxList) {
		this.boxList = boxList;
	}

	public int getInitAmount() {
		return initAmount;
	}

	public void setInitAmount(int initAmount) {
		this.initAmount = initAmount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDispenseAmount() {
		return dispenseAmount;
	}

	public void setDispenseAmount(int dispenseAmount) {
		this.dispenseAmount = dispenseAmount;
	}

	public int getRejectAmount() {
		return rejectAmount;
	}

	public void setRejectAmount(int rejectAmount) {
		this.rejectAmount = rejectAmount;
	}

	public int getRetractCount() {
		return retractCount;
	}

	public void setRetractCount(int retractCount) {
		this.retractCount = retractCount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

}
