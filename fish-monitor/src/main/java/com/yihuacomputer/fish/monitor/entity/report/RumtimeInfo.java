package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;

public class RumtimeInfo implements IRuntimeInfo {

	private String termId;
	
	/**
	 * 日期
	 */
	private String runDate;
	/**
	 * 拒钞率
	 */
	private double cashRefusedRate;
	/**
	 * 拒钞张数
	 */
	private long refusedNote;
	/**
	 * 点钞张数
	 */
	private long totalNote;
	/**
	 * 卡钞率
	 */
	private long cashError;
	/**
	 * 现金交易故障次数
	 */
	private long cashTransError;
	/**
	 * 存取款交易笔数
	 */
	private long cashTrans;
	/**
	 * 存取款交易张数
	 */
	private long cashNote;
	/**
	 * 其他交易笔数
	 */
	private long otherTrans;
	/**
	 * 设备开机率
	 */
	private double customRate;
	/**
	 * 设备维护率
	 */
	private double adminRate;
	/**
	 * 故障率
	 */
	private double stopRate;
	
	public String getTermId(){
		return this.termId;
	}
	public void setTermId(String termId){
		this.termId = termId;
	}
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public double getCashRefusedRate() {
		return cashRefusedRate;
	}
	public void setCashRefusedRate(double cashRefusedRate) {
		this.cashRefusedRate = cashRefusedRate;
	}
	public long getRefusedNote() {
		return refusedNote;
	}
	public void setRefusedNote(long refusedNote) {
		this.refusedNote = refusedNote;
	}
	public long getTotalNote() {
		return totalNote;
	}
	public void setTotalNote(long totalNote) {
		this.totalNote = totalNote;
	}
	public long getCashError() {
		return cashError;
	}
	public void setCashError(long cashError) {
		this.cashError = cashError;
	}
	public long getCashTransError() {
		return cashTransError;
	}
	public void setCashTransError(long cashTransError) {
		this.cashTransError = cashTransError;
	}
	public long getCashTrans() {
		return cashTrans;
	}
	public void setCashTrans(long cashTrans) {
		this.cashTrans = cashTrans;
	}
	public long getCashNote() {
		return cashNote;
	}
	public void setCashNote(long cashNote) {
		this.cashNote = cashNote;
	}
	public long getOtherTrans() {
		return otherTrans;
	}
	public void setOtherTrans(long otherTrans) {
		this.otherTrans = otherTrans;
	}
	public double getCustomRate() {
		return customRate;
	}
	public void setCustomRate(double customRate) {
		this.customRate = customRate;
	}
	public double getAdminRate() {
		return adminRate;
	}
	public void setAdminRate(double adminRate) {
		this.adminRate = adminRate;
	}
	public double getStopRate() {
		return stopRate;
	}
	public void setStopRate(double stopRate) {
		this.stopRate = stopRate;
	}
}
