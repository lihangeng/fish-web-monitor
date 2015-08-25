package com.yihuacomputer.fish.atmlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
/**
 * ATM应用日志
 */
@Entity
@Table(name = "ATMC_APP_LOGS")
public class AtmLog implements IAtmLog{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_APP_LOGS")
	@SequenceGenerator(name = "SEQ_ATMC_APP_LOGS", sequenceName = "SEQ_ATMC_APP_LOGS")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Column(name = "DATE_TIME",length = 20)
	private String dateTime;
	
	@Column(name = "DO_TIMES")
	private int doTimes;
	
	@Column(name = "LOG_SIZE")
	private long logSize;
	
	@Column(name = "LAST_DO_DATE",length = 20)
	private String lastDoDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "BACKUP_RESULT",length = 15)
	private BackupResult backupResult;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "CROWN_IMPOERT",columnDefinition="CHAR",length=1)
	private boolean isCrownImport;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getLogSize() {
		return logSize;
	}

	public void setLogSize(long logSize) {
		this.logSize = logSize;
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

	public int getDoTimes() {
		return doTimes;
	}

	public void setDoTimes(int doTimes) {
		this.doTimes = doTimes;
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

	public boolean isCrownImport() {
		return isCrownImport;
	}

	public void setCrownImport(boolean isCrownImport) {
		this.isCrownImport = isCrownImport;
	}	
	
}
