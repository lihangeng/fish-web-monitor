package com.yihuacomputer.fish.web.daily.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfo;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;

public class DayBackupLogForm {

	private String date;
	private DayBackupResult result;
	private String doTime;
	private String endTime;
	private int deviceCount;
	private int deviceSucCount;
	private int deviceFailCount;
	private static String lastDay;


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

	public static  List<DayBackupLogForm> toForms(List<IDayBackupLog> dayBackupLogs, Map<String,IAtmLogInfo>getBackUpInfo){
		List<DayBackupLogForm> forms = new ArrayList<DayBackupLogForm>();
		for(IDayBackupLog log : dayBackupLogs){
			DayBackupLogForm form = new DayBackupLogForm();
			form.setDate(log.getDate());
			form.setResult(log.getResult());
			form.setDoTime(log.getDoTime());
			form.setEndTime(log.getEndTime());
			if(getBackUpInfo.get(getLastDay(log.getDate())) != null)
			{
				form.setDeviceSucCount(getBackUpInfo.get(getLastDay(log.getDate())).getBackupSuccessNumber());
				form.setDeviceFailCount(getBackUpInfo.get(getLastDay(log.getDate())).getBackupErrorNumber());
			}
			else
			{
				form.setDeviceSucCount(0);
				form.setDeviceFailCount(0);				
			}
			forms.add(form);
		}
		return forms;
	}
	
	public static String getLastDay(String strDate)
	{
		Date date = DateUtils.getDate(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);		
		lastDay = new  SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());	
		return lastDay;
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
	public int getDeviceSucCount() {
		return deviceSucCount;
	}


	public void setDeviceSucCount(int deviceSucCount) {
		this.deviceSucCount = deviceSucCount;
	}


	public int getDeviceFailCount() {
		return deviceFailCount;
	}


	public void setDeviceFailCount(int deviceFailCount) {
		this.deviceFailCount = deviceFailCount;
	}
	
	
	
	
}
