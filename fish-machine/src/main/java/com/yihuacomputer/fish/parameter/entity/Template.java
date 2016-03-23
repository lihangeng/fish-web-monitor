package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.ITemplate;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name="PARAM_TEMPLATE")
public class Template implements ITemplate, Serializable {


	public static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_TEMPLATE")
    @SequenceGenerator(name = "SEQ_PARAM_TEMPLATE", sequenceName = "SEQ_PARAM_TEMPLATE")
    @Column(name = "ID")
    private long id;

	@Column(name="NAME",length=40)
	private String name;

	@Column(name="REMARK",length=40)
	private String remark;

	

	public Template(){

	}


	@Override
	public long getId() {
		return id;
	}


	@Override
	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getRemark() {
		return remark;
	}


	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
