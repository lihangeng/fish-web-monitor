package com.yihuacomputer.fish.monitor.entity.volume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolume;

@Entity
@Table(name = "ATMC_DAY_TRADING_VOLUME")
public class DayTradingVolume implements IDayTradingVolume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_DAY_TRADING_VOLUME")
	@SequenceGenerator(name = "SEQ_ATMC_DAY_TRADING_VOLUME", sequenceName = "SEQ_ATMC_DAY_TRADING_VOLUME")
	@Column(name = "ID")
	private long id;

	@Column(name = "TERMINAL_ID")
	private String terminalId;

	@Column(name = "AMT_IN")
	private double amtIn;

	@Column(name = "AMT_OUT")
	private double amtOut;

	@Column(name = "TRANS_DATE")
	private int transDate;

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

	public int getTransDate() {
		return transDate;
	}

	public void setTransDate(int transDate) {
		this.transDate = transDate;
	}

	public double getAmtIn() {
		return amtIn;
	}

	public void setAmtIn(double amtIn) {
		this.amtIn = amtIn;
	}

	public double getAmtOut() {
		return amtOut;
	}

	public void setAmtOut(double amtOut) {
		this.amtOut = amtOut;
	}

}
