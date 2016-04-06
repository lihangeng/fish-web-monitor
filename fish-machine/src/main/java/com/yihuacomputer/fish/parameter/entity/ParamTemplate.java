package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name = "PARAM_TEMPLATE")
public class ParamTemplate implements IParamTemplate, Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_TEMPLATE")
	@SequenceGenerator(name = "SEQ_PARAM_TEMPLATE", sequenceName = "SEQ_PARAM_TEMPLATE")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME", length = 40)
	private String name;

	@Column(name = "REMARK", length = 60)
	private String remark;

	@Column(name = "APPLY_FLAG", length = 1)
	private String applyFlag;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AppSystem.class)
	@JoinColumn(name = "PARAM_BELONGS")
	private IAppSystem paramBelongs;

	public ParamTemplate() {

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

	@Override
	public IAppSystem getParamBelongs() {
		return paramBelongs;
	}

	@Override
	public void setParamBelongs(IAppSystem paramBelongs) {
		this.paramBelongs = paramBelongs;
	}

	@Override
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	@Override
	public String getApplyFlag() {
		return this.applyFlag;
	}

}
