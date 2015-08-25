package com.yihuacomputer.fish.fault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.fault.IFilterCode;

@Entity
@Table(name = "CASE_FILTER")
public class FilterCode implements IFilterCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_FILTER")
	@SequenceGenerator(name = "SEQ_CASE_FILTER", sequenceName = "SEQ_CASE_FILTER")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getRemark() {
		return this.remark;
	}

}
