package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Entity
@Table(name = "etl_fault_classify_week")
public class FaultClassifyWeek implements IFaultClassifyWeek {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_fault_week")
	@SequenceGenerator(name = "SEQ_etl_fault_week", sequenceName = "SEQ_etl_fault_week")
	@Column(name = "ID")
	private long id;

	@Column(name = "STAT_DATE", length = 10)
	private long date;

	@Column(name = "CLASSIFY_ID")
	private long classifyId;

	@Column(name = "CLASSIFY_NAME", length = 30)
	private String classifyName;

	@Column(name = "CLASSIFY_COUNT")
	private long count;

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
	public long getClassifyId() {
		return classifyId;
	}

	@Override
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	@Override
	public String getClassifyName() {
		return classifyName;
	}

	@Override
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	@Override
	public long getCount() {
		return count;
	}

	@Override
	public void setCount(long count) {
		this.count = count;
	}

}
