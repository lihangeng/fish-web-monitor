package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.base.ITransactionDays;

@Entity
@Table(name = "ATMC_TRANSACTION_DAYS ")
public class TransactionDays implements ITransactionDays{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_TRANSACTION_DAYS")
    @SequenceGenerator(name = "SEQ_ATMC_TRANSACTION_DAYS", sequenceName = "SEQ_ATMC_TRANSACTION_DAYS")
    @Column(name = "ID")
	private long id;

	@Column(name = "TERMINAL_ID", length = 20)
	private String terminalId;

	@Column(name = "TRANS_DATE", length = 11)
	private long transDate;

	@Column(name = "TRANS_COUNT", length = 11)
	private long transCount;

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

	public long getTransDate() {
		return transDate;
	}

	public void setTransDate(long transDate) {
		this.transDate = transDate;
	}

	public long getTransCount() {
		return transCount;
	}

	public void setTransCount(long transCount) {
		this.transCount = transCount;
	}












}
