package com.yihuacomputer.fish.monitor.entity.volume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolume;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_TRADING_VOLUME_MONTH")
public class MonthDailyTradingVolume implements IMonthDailyTradingVolume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_TRADING_VOLUME_MONTH")
	@SequenceGenerator(name = "SEQ_DEV_TRADING_VOLUME_MONTH", sequenceName = "SEQ_DEV_TRADING_VOLUME_MONTH")
	@Column(name = "ID")
	private long id;
	@Column(name = "MONTH_IN_AVG")
	private double monthAmtInAvg;
	@Column(name = "MONTH_OUT_AVG")
	private double monthAmtOutAvg;
	@Column(name = "TERMINAL_ID")
	private String terminalId;
	@Column(name = "TRANS_MONTH")
	private int transMonth;
	@Column(name = "LAST_YEAR_IN_AVG")
	private double lastYearAmtInAvg;
	@Column(name = "LAST_YEAR_OUT_AVG")
	private double lastYearAmtOutAvg;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public double getMonthAmtInAvg() {
		return monthAmtInAvg;
	}

	@Override
	public void setMonthAmtInAvg(double monthAmtInAvg) {
		this.monthAmtInAvg = monthAmtInAvg;
	}

	@Override
	public double getMonthAmtOutAvg() {
		return monthAmtOutAvg;
	}

	@Override
	public void setMonthAmtOutAvg(double monthAmtOutAvg) {
		this.monthAmtOutAvg = monthAmtOutAvg;
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
	public int getTransMonth() {
		return transMonth;
	}

	@Override
	public void setTransMonth(int transMonth) {
		this.transMonth = transMonth;
	}

	@Override
	public double getLastYearAmtInAvg() {
		return lastYearAmtInAvg;
	}

	@Override
	public void setLastYearAmtInAvg(double lastYearAmtInAvg) {
		this.lastYearAmtInAvg = lastYearAmtInAvg;
	}

	@Override
	public double getLastYearAmtOutAvg() {
		return lastYearAmtOutAvg;
	}

	@Override
	public void setLastYearAmtOutAvg(double lastYearAmtOutAvg) {
		this.lastYearAmtOutAvg = lastYearAmtOutAvg;
	}

}
