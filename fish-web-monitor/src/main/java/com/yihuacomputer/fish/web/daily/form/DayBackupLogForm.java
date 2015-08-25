package com.yihuacomputer.fish.web.daily.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;

public class DayBackupLogForm {

	private String date;
	private DayBackupResult result;
	private String doTime;
	private String endTime;
	private int deviceCount;
	
	
	public DayBackupLogForm() {
	}


	public DayBackupLogForm(String date, DayBackupResult result, String doTime,
			String endTime, int deviceCount) {
		this.date = date;
		this.result = result;
		this.doTime = doTime;
		this.endTime = endTime;
		this.deviceCount = deviceCount;
	}

	public static List<DayBackupLogForm> toForms(List<IDayBackupLog> dayBackupLogs){
		List<DayBackupLogForm> forms = new ArrayList<DayBackupLogForm>();
		for(IDayBackupLog log : dayBackupLogs){
			DayBackupLogForm form = new DayBackupLogForm();
			form.setDate(log.getDate());
			form.setResult(log.getResult());
			form.setDoTime(log.getDoTime());
			form.setEndTime(log.getEndTime());
			form.setDeviceCount(log.getDeviceCount());
			forms.add(form);
		}
		return forms;
	}
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public DayBackupResult getResult() {
		return result;
	}


	public void setResult(DayBackupResult result) {
		this.result = result;
	}


	public String getDoTime() {
		return doTime;
	}


	public void setDoTime(String doTime) {
		this.doTime = doTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public int getDeviceCount() {
		return deviceCount;
	}


	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}
	
	
	
	
	
}
