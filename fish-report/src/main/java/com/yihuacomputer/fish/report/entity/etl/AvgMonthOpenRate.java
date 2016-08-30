package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.openRate.etl.IAvgMonthOpenRate;

/**
 * 
 * @author xuxiang
 *
 */
@Entity
@Table(name ="etl_avg_month_open_rate")
public class AvgMonthOpenRate implements IAvgMonthOpenRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_avg_month_open_rate")
	@SequenceGenerator(name = "SEQ_etl_avg_month_open_rate", sequenceName = "SEQ_etl_avg_month_open_rate")
	@Column(name = "ID")
	private long id;

	@Override
	public long geId() {
		return this.id;
	}
	
	/**
	 * 统计日期
	 */
	@Column(name = "STAT_DATE", length = 10)
	private String date;

	/** 设备应开机时长 */
	@Column(name = "OPENTIMES")
	private long openTimes;

	/** 设备正常时间段内实际开机时长 */
	@Column(name = "HEALTHY_TIMEREAL")
	private long healthyTimeReal;

	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
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
	public double getRate() {
		return this.getHealthyTimeReal() / this.getOpenTimes();
	}
}
