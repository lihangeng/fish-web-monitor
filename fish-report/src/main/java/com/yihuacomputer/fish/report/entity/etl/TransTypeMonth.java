package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeMonth;
/**
 * 
 * @author xuxiang
 *
 */
@Entity
@Table(name = "etl_trans_type_month")
public class TransTypeMonth implements ITransTypeMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_trans_type_month")
	@SequenceGenerator(name = "SEQ_etl_trans_type_month", sequenceName = "SEQ_etl_trans_type_month")
	@Column(name = "ID")
	private long id;

	@Column(name = "STAT_DATE", length = 10)
	private long date;

	@Column(name = "TRANS_CODE", length = 20)
	private String transCode;

	@Column(name = "TRANS_COUNT")
	private long transCount;

	@Column(name = "TRANS_AMOUNT")
	private double transAmount;

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
	public String getTransCode() {
		return transCode;
	}

	@Override
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	@Override
	public long getTransCount() {
		return transCount;
	}

	@Override
	public void setTransCount(long transCount) {
		this.transCount = transCount;
	}

	@Override
	public double getTransAmount() {
		return transAmount;
	}

	@Override
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

}
