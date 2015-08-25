package com.yihuacomputer.fish.atmlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;
@Entity
@Table(name = "ATMC_DAYBACKUP_LOG")
public class DayBackupLog implements IDayBackupLog {

	@Id
	@Column(name = "DAYBACKUP_DATE",length=20,nullable=false)
	private String date;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RESULT",length=10)
	private DayBackupResult result;
	
	@Column(name = "DO_TIME",length=20)
	private String doTime;
	
	@Column(name = "END_TIME",length=20)
	private String endTime;
	
	@Column(name = "DEVICE_COUNT")
	private int deviceCount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DayBackupResult getResult() {
		return result;
	}

	public void setResult(DayBackupResult result) {
		this.result = result;
	}

	public String getDoTime() {
		return doTime;
	}

	public void setDoTime(String doTime) {
		this.doTime = doTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}	
}
