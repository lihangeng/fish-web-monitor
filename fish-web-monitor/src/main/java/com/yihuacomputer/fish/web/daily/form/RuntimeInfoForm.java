package com.yihuacomputer.fish.web.daily.form;
import java.util.List;

import com.yihuacomputer.fish.web.monitor.form.HardwareVersion;

/**
 * @author YiHua
 *
 */
public class RuntimeInfoForm {

	private long id;
	/**
	 * 内部序号
	 */
	private long serialno;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * 拒钞率
	 */
	private double cashRefusedRate;
	/**
	 * 拒钞张数
	 */
	private long refusedNoteCount;
	/**
	 * 点钞张数
	 */
	private long totalNoteCount;
	/**
	 * 卡钞率（故障率）
	 */
	private double cashErrorRate;
	/**
	 * 现金交易故障次数
	 */
	private long cashTransError;
	/**
	 * 存取款交易笔数
	 */
	private long cashTransCount;
	/**
	 * 存取款交易张数
	 */
	private long cashNoteCount;
	/**
	 * 其他交易笔数
	 */
	private long otherTransCount;
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
	/**
	 * 获取硬件版本号. 存取款模块，CABC，日志，凭条打印机，EPP，验钞模块的firmware版本信息。 每个模块主版本号MAIN
	 */
	private List<HardwareVersion> listHardwareVersion;
	/**
	 * 获取软件版本号. SP，AP
	 */
	private List<RuntimeVersionForm> listSoftwareVersion;

	private String startHitTime;

	private String lastHitTime;

	private long customHits;

	private long adminHits;

	private long stopHits;
	
	private String appRet;

	public RuntimeInfoForm() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSerialno() {
		return serialno;
	}

	public void setSerialno(long serialno) {
		this.serialno = serialno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getRefusedNoteCount() {
		return refusedNoteCount;
	}

	public void setRefusedNoteCount(long refusedNoteCount) {
		this.refusedNoteCount = refusedNoteCount;
	}

	public long getTotalNoteCount() {
		return totalNoteCount;
	}

	public void setTotalNoteCount(long totalNoteCount) {
		this.totalNoteCount = totalNoteCount;
	}

	public long getCashTransError() {
		return cashTransError;
	}

	public void setCashTransError(long cashTransError) {
		this.cashTransError = cashTransError;
	}

	public long getCashTransCount() {
		return cashTransCount;
	}

	public void setCashTransCount(long cashTransCount) {
		this.cashTransCount = cashTransCount;
	}

	public long getCashNoteCount() {
		return cashNoteCount;
	}

	public void setCashNoteCount(long cashNoteCount) {
		this.cashNoteCount = cashNoteCount;
	}

	public long getOtherTransCount() {
		return otherTransCount;
	}

	public void setOtherTransCount(long otherTransCount) {
		this.otherTransCount = otherTransCount;
	}

	public List<HardwareVersion> getListHardwareVersion() {
		return listHardwareVersion;
	}

	public void setListHardwareVersion(List<HardwareVersion> listHardwareVersion) {
		this.listHardwareVersion = listHardwareVersion;
	}

	public List<RuntimeVersionForm> getListSoftwareVersion() {
		return listSoftwareVersion;
	}

	public void setListSoftwareVersion(
			List<RuntimeVersionForm> listSoftwareVersion) {
		this.listSoftwareVersion = listSoftwareVersion;
	}

	public double getCashRefusedRate() {
		return cashRefusedRate;
	}

	public void setCashRefusedRate(double cashRefusedRate) {
		this.cashRefusedRate = cashRefusedRate;
	}

	public double getCashErrorRate() {
		return cashErrorRate;
	}

	public void setCashErrorRate(double cashErrorRate) {
		this.cashErrorRate = cashErrorRate;
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

	public String getStartHitTime() {
		return startHitTime;
	}

	public void setStartHitTime(String startHitTime) {
		this.startHitTime = startHitTime;
	}

	public String getLastHitTime() {
		return lastHitTime;
	}

	public void setLastHitTime(String lastHitTime) {
		this.lastHitTime = lastHitTime;
	}

	public long getCustomHits() {
		return customHits;
	}

	public void setCustomHits(long customHits) {
		this.customHits = customHits;
	}

	public long getAdminHits() {
		return adminHits;
	}

	public void setAdminHits(long adminHits) {
		this.adminHits = adminHits;
	}

	public long getStopHits() {
		return stopHits;
	}

	public void setStopHits(long stopHits) {
		this.stopHits = stopHits;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}
	
	

}
