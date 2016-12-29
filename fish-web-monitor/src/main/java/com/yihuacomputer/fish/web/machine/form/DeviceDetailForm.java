package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.web.version.form.DeviceVersionHistory;
import com.yihuacomputer.fish.web.version.form.VersionForm;

/**
 * @author YiHua
 *
 */
public class DeviceDetailForm {
	
	
	private IStatusReport statusReport;
	
	private List<VersionForm> appReleaseList = new ArrayList<VersionForm>();
	private List<DeviceVersionHistory> versionDeviceList= new ArrayList<DeviceVersionHistory>();
	
	public List<DeviceVersionHistory> getVersionDeviceList() {
		return versionDeviceList;
	}
	public void setVersionDeviceList(List<DeviceVersionHistory> versionDeviceList) {
		this.versionDeviceList = versionDeviceList;
	}
	public IStatusReport getStatusReport() {
		return statusReport;
	}
	public void setStatusReport(IStatusReport statusReport) {
		this.statusReport = statusReport;
	}
	public List<VersionForm> getAppReleaseList() {
		return appReleaseList;
	}
	public void setAppReleaseList(List<VersionForm> appReleaseList) {
		this.appReleaseList = appReleaseList;
	}
	
}
