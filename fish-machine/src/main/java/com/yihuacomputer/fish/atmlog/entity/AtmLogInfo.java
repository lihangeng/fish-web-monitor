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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int getBackupSuccessNumber() {
		return backupSuccessNumber;
	}

	@Override
	public void setBackupSuccessNumber(int backupSuccessNumber) {
		this.backupSuccessNumber = backupSuccessNumber;
	}

	@Override
	public int getBackupErrorNumber() {
		return backupErrorNumber;
	}

	@Override
	public void setBackupErrorNumber(int backupErrorNumber) {
		this.backupErrorNumber = backupErrorNumber;
	}

	@Override
	public int getTotalBackupNumber() {
		return totalBackupNumber;
	}

	@Override
	public void setTotalBackupNumber(int totalBackupNumber) {
		this.totalBackupNumber = totalBackupNumber;
	}

	@Override
	public Date getBackupDate() {
		return backupDate;
	}

	@Override
	public void setBackupDate(Date backupDate) {
		this.backupDate = backupDate;
	}

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
