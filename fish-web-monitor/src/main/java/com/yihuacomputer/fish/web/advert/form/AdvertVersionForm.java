package com.yihuacomputer.fish.web.advert.form;

import java.util.Date;

import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionStatus;

public class AdvertVersionForm {
	private long id;
	private Date createdTime;
	private String versionNo;
	private String versionPath;
	private String versionFile;
	private VersionStatus versionStatus;
	private String createdUser = "admin";
	private boolean exist = false;// 是否存在版本信息,默认不存在

	public AdvertVersionForm() {
	}

	public AdvertVersionForm(IVersion version) {
		if (version != null) {
			this.exist = true;
			this.id = version.getId();
			this.createdTime = version.getCreatedTime();
			this.versionFile = version.getServerPath();
			this.versionPath = version.getVersionPath();
			this.versionStatus = version.getVersionStatus();
			this.versionNo = version.getVersionNo();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionPath() {
		return versionPath;
	}

	public void setVersionPath(String versionPath) {
		this.versionPath = versionPath;
	}

	public String getVersionFile() {
		return versionFile;
	}

	public void setVersionFile(String versionFile) {
		this.versionFile = versionFile;
	}

	public VersionStatus getVersionStatus() {
		return versionStatus;
	}

	public void setVersionStatus(VersionStatus versionStatus) {
		this.versionStatus = versionStatus;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
