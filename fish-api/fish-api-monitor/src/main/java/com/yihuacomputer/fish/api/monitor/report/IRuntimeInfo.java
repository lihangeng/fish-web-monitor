package com.yihuacomputer.fish.api.monitor.report;

public interface IRuntimeInfo {
	
	public String getTermId();
	
	public void setTermId(String termId);
	/**
	 * 获取信息报告日期
	 * @return
	 */
	public String getRunDate();
	
	/**
	 * 设置信息报告日期
	 * @param runDate
	 */
	public void setRunDate(String runDate);
	
	/**
	 * 获取存款点钞张数
	 * @return
	 */
	public long getTotalNote();
	
	/**
	 * 设置存款点钞张数
	 * @param totalNote
	 */
	public void setTotalNote(long totalNote);
	
	/**
	 * 获取拒钞张数
	 * @return
	 */
	public long getRefusedNote();
	
	/**
	 *	接收拒钞张数
	 * @param refused_note
	 */
	public void setRefusedNote(long refusedNote);
	
	/**
	 * 获取存取现金张数
	 * @return
	 */
	public long getCashNote();
	
	/**
	 * 设置存取现金张数
	 * @param cash_note
	 */
	public void setCashNote(long cashNote);
	
	/**
	 * 获取存取交易笔数
	 * @return
	 */
	public long getCashTrans();
	
	/**
	 * 设置存取交易笔数
	 * @param cash_trans
	 */
	public void setCashTrans(long cashTrans);
	
	/**
	 * 获取卡钞交易笔数
	 * @return
	 */
	public long getCashError();
	
	/**
	 * 设置卡钞交易笔数
	 * @param cash_error
	 */
	public void setCashError(long cashError);
	
	/**
	 * 获取非现金交易笔数
	 * @return
	 */
	public long getOtherTrans();
	
	/**
	 * 设置非现金交易笔数
	 * @param other_trans
	 */
	public void setOtherTrans(long otherTrans);
	
	/**
	 * 获取设备开机率
	 * @return
	 */
	public double getCustomRate();
	
	/**
	 * 设置设备开机率
	 * @param customRate
	 */
	public void setCustomRate(double customRate);
	
	/**
	 * 获取设备维护率
	 * @return
	 */
	public double getAdminRate();
	
	/**
	 * 设置设备维护率
	 * @param adminRate
	 */
	public void setAdminRate(double adminRate);
	
	/**
	 * 获取设备故障率
	 * @return
	 */
	public double getStopRate();
	
	/**
	 * 设置设备故障率
	 * @param stopRate
	 */
	public void setStopRate(double stopRate);
}
