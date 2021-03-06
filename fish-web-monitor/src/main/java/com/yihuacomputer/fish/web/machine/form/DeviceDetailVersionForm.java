package com.yihuacomputer.fish.web.machine.form;

import com.yihuacomputer.fish.web.version.form.VersionForm;

/**
 * @author YiHua
 *
 */
public class DeviceDetailVersionForm {
	
	private String currentVersion;
	
	private VersionForm maxVersion;

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

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
