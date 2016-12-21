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
	
	@Override
	public String getTermId(){
		return this.termId;
	}
	
	@Override
	public void setTermId(String termId){
		this.termId = termId;
	}
	
	@Override
	public String getRunDate() {
		return runDate;
	}
	
	@Override
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	
	public double getCashRefusedRate() {
		return cashRefusedRate;
	}
	
	public void setCashRefusedRate(double cashRefusedRate) {
		this.cashRefusedRate = cashRefusedRate;
	}
	
	@Override
	public long getRefusedNote() {
		return refusedNote;
	}
	
	@Override
	public void setRefusedNote(long refusedNote) {
		this.refusedNote = refusedNote;
	}
	
	@Override
	public long getTotalNote() {
		return totalNote;
	}
	
	@Override
	public void setTotalNote(long totalNote) {
		this.totalNote = totalNote;
	}
	
	@Override
	public long getCashError() {
		return cashError;
	}
	
	@Override
	public void setCashError(long cashError) {
		this.cashError = cashError;
	}
	
	public long getCashTransError() {
		return cashTransError;
	}
	
	public void setCashTransError(long cashTransError) {
		this.cashTransError = cashTransError;
	}
	
	@Override
	public long getCashTrans() {
		return cashTrans;
	}
	
	@Override
	public void setCashTrans(long cashTrans) {
		this.cashTrans = cashTrans;
	}
	
	@Override
	public long getCashNote() {
		return cashNote;
	}
	
	@Override
	public void setCashNote(long cashNote) {
		this.cashNote = cashNote;
	}
	
	@Override
	public long getOtherTrans() {
		return otherTrans;
	}
	
	@Override
	public void setOtherTrans(long otherTrans) {
		this.otherTrans = otherTrans;
	}
	
	@Override
	public double getCustomRate() {
		return customRate;
	}
	
	@Override
	public void setCustomRate(double customRate) {
		this.customRate = customRate;
	}
	
	@Override
	public double getAdminRate() {
		return adminRate;
	}
	
	@Override
	public void setAdminRate(double adminRate) {
		this.adminRate = adminRate;
	}
	
	@Override
	public double getStopRate() {
		return stopRate;
	}
	
	@Override
	public void setStopRate(double stopRate) {
		this.stopRate = stopRate;
	}
}
