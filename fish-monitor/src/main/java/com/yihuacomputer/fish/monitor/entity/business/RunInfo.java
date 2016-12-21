package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;

@Entity
@Table(name = "ATMC_STATUS_HIST")
public class RunInfo implements IRunInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_STATUS_HIST")
	@SequenceGenerator(name = "SEQ_ATMC_STATUS_HIST", sequenceName = "SEQ_ATMC_STATUS_HIST")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RUN_STATUS",length = 15)
	private RunStatus runStatus;
	
	@Column(name = "STATUS_TIME",length = 20)
	private String statusTime;
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public String getStatusTime() {
		return statusTime;
	}

	@Override
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	@Override
	public void setRunStatus(RunStatus runStatus) {
		this.runStatus = runStatus;
	}

	@Override
	public RunStatus getRunStatus() {
		return this.runStatus;
	}

}
