package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.fault.etl.IFaultMonth;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Entity
@Table(name = "etl_fault_month")
public class FaultMonth implements IFaultMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_fault_month")
	@SequenceGenerator(name = "SEQ_etl_fault_month", sequenceName = "SEQ_etl_fault_month")
	@Column(name = "ID")
	private long id;

	@Column(name = "STAT_DATE", length = 10)
	private long date;

	@Column(name = "OPEN_COUNT")
	private long openCount;

	@Column(name = "CLOSE_COUNT")
	private long closeCount;

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
	public long getOpenCount() {
		return openCount;
	}

	@Override
	public void setOpenCount(long openCount) {
		this.openCount = openCount;
	}

	@Override
	public long getCloseCount() {
		return closeCount;
	}

	@Override
	public void setCloseCount(long closeCount) {
		this.closeCount = closeCount;
	}

}
