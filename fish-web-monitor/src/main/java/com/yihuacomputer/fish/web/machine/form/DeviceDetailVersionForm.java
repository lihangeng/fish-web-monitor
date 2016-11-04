package com.yihuacomputer.fish.web.machine.form;

import java.util.List;

import com.yihuacomputer.fish.web.version.form.VersionForm;

public class DeviceDetailVersionForm {
	
	private String currentVersion;
	
	private VersionForm maxVersion;

//	private List<VersionForm> updateVersion;

	private String lastUpdateTime;

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public VersionForm getMaxVersion() {
		return maxVersion;
	}

	public void setMaxVersion(VersionForm maxVersion) {
		this.maxVersion = maxVersion;
	}

//	public List<VersionForm> getUpdateVersion() {
//		return updateVersion;
//	}
//
//	public void setUpdateVersion(List<VersionForm> updateVersion) {
//		this.updateVersion = updateVersion;
//	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
