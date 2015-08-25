package com.yihuacomputer.fish.web.daily.form;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfo;

public class AtmLogInfoForm {

	private long id ;

	private String orgId;

	private String orgName;

	private int backupSuccessNumber;

	private int backupErrorNumber;

	private int totalBackupNumber;

	private String backupDate;

	public AtmLogInfoForm(){

	}

	public AtmLogInfoForm(IAtmLogInfo atmLogInfo){
		this.orgId = String.valueOf(atmLogInfo.getId());
		this.backupDate = DateUtils.getDate(atmLogInfo.getBackupDate());
		setBackupErrorNumber(atmLogInfo.getBackupErrorNumber());
		setBackupSuccessNumber(atmLogInfo.getBackupSuccessNumber());
		setOrgName(atmLogInfo.getOrgName());
		setTotalBackupNumber(atmLogInfo.getTotalBackupNumber());

	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getBackupDate() {
		return backupDate;
	}

	public void setBackupDate(String backupDate) {
		this.backupDate = backupDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



}
