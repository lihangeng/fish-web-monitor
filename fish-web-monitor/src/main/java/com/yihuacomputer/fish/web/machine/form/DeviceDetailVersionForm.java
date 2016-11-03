package com.yihuacomputer.fish.web.machine.form;

import java.util.List;

import com.yihuacomputer.fish.web.version.form.VersionForm;

public class DeviceDetailVersionForm {
	
	private VersionForm currentVersion;
	
	private VersionForm maxVersion;

	private List<VersionForm> updateVersion;

	private String lastUpdateTime;

	public VersionForm getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(VersionForm currentVersion) {
		this.currentVersion = currentVersion;
	}

	public VersionForm getMaxVersion() {
		return maxVersion;
	}

	public void setMaxVersion(VersionForm maxVersion) {
		this.maxVersion = maxVersion;
	}

	public List<VersionForm> getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(List<VersionForm> updateVersion) {
		this.updateVersion = updateVersion;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
