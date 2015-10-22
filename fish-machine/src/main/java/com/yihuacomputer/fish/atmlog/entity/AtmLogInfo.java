package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;
import java.util.Date;

import com.yihuacomputer.fish.api.atmlog.IAtmLogInfo;

public class AtmLogInfo implements IAtmLogInfo,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8165898395421364503L;

	private long id;

	private String orgName;

	private int backupSuccessNumber;

	private int backupErrorNumber;

	private int totalBackupNumber;

	private Date backupDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBackupSuccessNumber() {
		return backupSuccessNumber;
	}

	public void setBackupSuccessNumber(int backupSuccessNumber) {
		this.backupSuccessNumber = backupSuccessNumber;
	}

	public int getBackupErrorNumber() {
		return backupErrorNumber;
	}

	public void setBackupErrorNumber(int backupErrorNumber) {
		this.backupErrorNumber = backupErrorNumber;
	}

	public int getTotalBackupNumber() {
		return totalBackupNumber;
	}

	public void setTotalBackupNumber(int totalBackupNumber) {
		this.totalBackupNumber = totalBackupNumber;
	}

	public Date getBackupDate() {
		return backupDate;
	}

	public void setBackupDate(Date backupDate) {
		this.backupDate = backupDate;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
