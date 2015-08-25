package com.yihuacomputer.fish.atmlog.entity;

import java.util.Date;

import com.yihuacomputer.fish.api.atmlog.IAtmLogGlobalStatistics;

public class AtmLogGlobalStatistics implements IAtmLogGlobalStatistics {
	
	private long id;
	
	private String orgName;

	private String orgCode;
	
	private String deviceVendor;

	private int backupSuccessNumber;

	private int backupErrorNumber;

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
	public String getOrgName() {
		return orgName;
	}

	@Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getDeviceVendor() {
		return deviceVendor;
	}

	@Override
	public void setDeviceVendor(String deviceVendor) {
		this.deviceVendor = deviceVendor;
	}

	@Override
	public int getBackupSuccessNumber() {
		return backupSuccessNumber;
	}

	@Override
	public void setBackupSuccessNumber(int backupSuccessNumber) {
		this.backupSuccessNumber =backupSuccessNumber;
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
    public String getOrgCode() {
        return orgCode;
    }

    @Override
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

}
