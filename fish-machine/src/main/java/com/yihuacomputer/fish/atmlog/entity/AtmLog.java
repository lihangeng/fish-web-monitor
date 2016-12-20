package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;

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
public class AtmLog implements IAtmLog,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2845793070494260038L;

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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public long getLogSize() {
		return logSize;
	}

	@Override
	public void setLogSize(long logSize) {
		this.logSize = logSize;
	}

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String getDateTime() {
		return dateTime;
	}

	@Override
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int getDoTimes() {
		return doTimes;
	}

	@Override
	public void setDoTimes(int doTimes) {
		this.doTimes = doTimes;
	}

	@Override
	public String getLastDoDate() {
		return lastDoDate;
	}

	@Override
	public void setLastDoDate(String lastDoDate) {
		this.lastDoDate = lastDoDate;
	}

	@Override
	public BackupResult getBackupResult() {
		return backupResult;
	}

	@Override
	public void setBackupResult(BackupResult backupResult) {
		this.backupResult = backupResult;
	}

	@Override
	public boolean isCrownImport() {
		return isCrownImport;
	}

	@Override
	public void setCrownImport(boolean isCrownImport) {
		this.isCrownImport = isCrownImport;
	}	
	
}
