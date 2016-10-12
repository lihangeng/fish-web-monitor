package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.version.form.DeviceVersionHistory;

public class DeviceDetailForm {
	
	
	private DeviceForm deviceForm;
	private IStatusReport statusReport;
	
    
	

	private String maxAlarm;
	private String minAlarm;

	private List<PersonForm> personList = new ArrayList<PersonForm>();
	private List<DeviceVersionHistory> versionDeviceList= new ArrayList<DeviceVersionHistory>();
	
	public String getMaxAlarm() {
		return maxAlarm;
	}
	public void setMaxAlarm(String maxAlarm) {
		this.maxAlarm = maxAlarm;
	}
	public String getMinAlarm() {
		return minAlarm;
	}
	public void setMinAlarm(String minAlarm) {
		this.minAlarm = minAlarm;
	}
	public List<PersonForm> getPersonList() {
		return personList;
	}
	public void setPersonList(List<PersonForm> personList) {
		this.personList = personList;
	}
	public List<DeviceVersionHistory> getVersionDeviceList() {
		return versionDeviceList;
	}
	public void setVersionDeviceList(List<DeviceVersionHistory> versionDeviceList) {
		this.versionDeviceList = versionDeviceList;
	}
	public DeviceForm getDeviceForm() {
		return deviceForm;
	}
	public void setDeviceForm(DeviceForm deviceForm) {
		this.deviceForm = deviceForm;
	}
	public IStatusReport getStatusReport() {
		return statusReport;
	}
	public void setStatusReport(IStatusReport statusReport) {
		this.statusReport = statusReport;
	}
	
}
