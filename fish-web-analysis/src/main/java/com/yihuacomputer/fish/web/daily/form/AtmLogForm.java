package com.yihuacomputer.fish.web.daily.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;

public class AtmLogForm {
	
	private long id;
	private String terminalId;
	private String dateTime;
	private String lastDoDate;
	private long size;
	private BackupResult backupResult;
	
	
	public AtmLogForm() {
	}


	public AtmLogForm(long id, String terminalId, String dateTime, String lastDoDate,long  size,
			BackupResult backupResult) {
		this.id = id;
		this.terminalId = terminalId;
		this.dateTime = dateTime;
		this.lastDoDate = lastDoDate;
		this.size = size;
		this.backupResult = backupResult;
	}
	
	public static List<AtmLogForm> toForms(List<IAtmLog> atmLogs){
		List<AtmLogForm> forms = new ArrayList<AtmLogForm>();
		for(IAtmLog atmLog : atmLogs){
			AtmLogForm form = new AtmLogForm();
			form.setId(atmLog.getId());
			form.setTerminalId(atmLog.getTerminalId());
			form.setDateTime(atmLog.getDateTime());
			form.setLastDoDate(atmLog.getLastDoDate());
			form.setSize(atmLog.getLogSize());
			form.setBackupResult(atmLog.getBackupResult());
			forms.add(form);
		}
		return forms;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTerminalId() {
		return terminalId;
	}


	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getLastDoDate() {
		return lastDoDate;
	}


	public void setLastDoDate(String lastDoDate) {
		this.lastDoDate = lastDoDate;
	}


	public BackupResult getBackupResult() {
		return backupResult;
	}


	public void setBackupResult(BackupResult backupResult) {
		this.backupResult = backupResult;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}
	
	
	
	

}
