package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationMonth;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Entity
@Table(name = "etl_fault_duration_month")
public class FaultDurationMonth implements IFaultDurationMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_fault_duration_month")
	@SequenceGenerator(name = "SEQ_etl_fault_duration_month", sequenceName = "SEQ_etl_fault_duration_month")
	@Column(name = "ID")
	private long id;

	@Column(name = "STAT_DATE", length = 10)
	private long date;

	@Column(name = "DURATION_ID")
	private int duration;

	@Column(name = "FAULT_COUNT")
	private long faultCount;

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	public int getDuration() {
		return duration;
	}

	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public long getCount() {
		return faultCount;
	}

	@Override
	public void setCount(long count) {
		this.faultCount = count;
	}

	@Override
	public String getDurationName() {
		return null;
	}
}
