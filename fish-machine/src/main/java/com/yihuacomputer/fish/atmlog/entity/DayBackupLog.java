package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;

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
public class DayBackupLog implements IDayBackupLog,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770536884041695828L;

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

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public DayBackupResult getResult() {
		return result;
	}

	@Override
	public void setResult(DayBackupResult result) {
		this.result = result;
	}

	@Override
	public String getDoTime() {
		return doTime;
	}

	@Override
	public void setDoTime(String doTime) {
		this.doTime = doTime;
	}
	
	@Override
	public String getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public int getDeviceCount() {
		return deviceCount;
	}

	@Override
	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}	
}
