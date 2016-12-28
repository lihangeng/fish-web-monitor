package com.yihuacomputer.fish.monitor.entity.volume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolume;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_TRADING_VOLUME_DAY")
public class DayTradingVolume implements IDayTradingVolume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_TRADING_VOLUME_DAY")
	@SequenceGenerator(name = "SEQ_DEV_TRADING_VOLUME_DAY", sequenceName = "SEQ_DEV_TRADING_VOLUME_DAY")
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
	public int getTransDate() {
		return transDate;
	}

	@Override
	public void setTransDate(int transDate) {
		this.transDate = transDate;
	}

	@Override
	public double getAmtIn() {
		return amtIn;
	}

	@Override
	public void setAmtIn(double amtIn) {
		this.amtIn = amtIn;
	}

	@Override
	public double getAmtOut() {
		return amtOut;
	}

	@Override
	public void setAmtOut(double amtOut) {
		this.amtOut = amtOut;
	}

}
