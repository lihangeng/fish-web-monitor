package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;

/**
 * 
 * @author xuxiang
 *
 */
@Entity
@Table(name = "etl_org_open_rate_week")
public class OrgOpenRateWeek implements IOrgOpenRateWeek {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_org_open_rate_week")
	@SequenceGenerator(name = "SEQ_etl_org_open_rate_week", sequenceName = "SEQ_etl_org_open_rate_week")
	@Column(name = "ID")
	private long id;

	@Column(name = "ORG_CODE", length = 40)
	private String orgCode;

	@Column(name = "ORG_NAME", length = 40)
	private String orgName;

	@Column(name = "START_DATE", length = 10)
	private String startDate;

	@Column(name = "END_DATE", length = 10)
	private String endDate;

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

	@Override
	public String getOrgCode() {
		return this.orgCode;
	}

	@Override
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Override
	public String getOrgName() {
		return this.orgName;
	}

	@Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
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
}
