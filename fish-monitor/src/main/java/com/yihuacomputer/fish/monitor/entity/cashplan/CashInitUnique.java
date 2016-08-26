package com.yihuacomputer.fish.monitor.entity.cashplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.box.ICashInitUnique;

@Entity
@Table(name = "ATMC_CASH_INIT_UNIQUE")
public class CashInitUnique implements ICashInitUnique {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_CASH_INIT_UNIQUE")
	@SequenceGenerator(name = "SEQ_ATMC_CASH_INIT_UNIQUE", sequenceName = "SEQ_ATMC_CASH_INIT_UNIQUE")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Column(name = "CASH_DATE",length=20)
	private String date;
	
	@Column(name = "UUID",length=20)
	private String uuId;
	
	@Column(name = "AMT")
	private long amt;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public long getAmt() {
		return amt;
	}

	public void setAmt(long amt) {
		this.amt = amt;
	}

}
