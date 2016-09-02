package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.openRate.etl.IAvgWeekOpenRate;

/**
 * 
 * @author xuxiang
 *
 */
@Entity
@Table(name = "etl_avg_week_open_rate")
public class AvgWeekOpenRate implements IAvgWeekOpenRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_avg_week_open_rate")
	@SequenceGenerator(name = "SEQ_etl_avg_week_open_rate", sequenceName = "SEQ_etl_avg_week_open_rate")
	@Column(name = "ID")
	private long id;

	@Column(name = "START_DATE", length = 10)
	private String startDate;

	@Column(name = "END_DATE", length = 10)
	private String endDate;
	
	/**
	 * 统计日期
	 */
	@Column(name = "STAT_DATE", length = 10)
	private long date;

	/** 设备应开机时长 */
	@Column(name = "OPENTIMES")
	private long openTimes;

	/** 设备正常时间段内实际开机时长 */
	@Column(name = "HEALTHY_TIMEREAL")
	private long healthyTimeReal;

	@Column(name = "OPEN_RATE")
	private double openRate;
	
	@Override
	public double getOpenRate() {
		return this.openRate;
	}

	@Override
	public void setOpenRate(double openRate) {
		this.openRate = openRate;
	}

	@Override
	public long getDate() {
		return date;
	}

	@Override
	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public long getOpenTimes() {
		return openTimes;
	}

	@Override
	public void setOpenTimes(long openTimes) {
		this.openTimes = openTimes;
	}

	@Override
	public long getHealthyTimeReal() {
		return healthyTimeReal;
	}

	@Override
	public void setHealthyTimeReal(long healthyTimeReal) {
		this.healthyTimeReal = healthyTimeReal;
	}
	
	@Override
	public long geId() {
		return this.id;
	}

	@Override
	public String getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
