package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IParamTemplate;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name="PARAM_TEMPLATE")
public class ParamTemplate implements IParamTemplate, Serializable {


	public static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_TEMPLATE")
    @SequenceGenerator(name = "SEQ_PARAM_TEMPLATE", sequenceName = "SEQ_PARAM_TEMPLATE")
    @Column(name = "ID")
    private long id;

	@Column(name="NAME",length=40)
	private String name;

	@Column(name="REMARK",length=60)
	private String remark;
	
	
	@Column(name="PARAM_KEY",length=40)
	private String paramKey;
	
	
	@Column(name="PARAM_VALUE",length=60)
	private String paramValue;
	
	
	@Column(name="VERSION_NO")
	private long versionNo;
	

	public ParamTemplate(){

	}

	
	@Override
	public String getParamKey() {
		return paramKey;
	}

	
	@Override
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	
	@Override
	public String getParamValue() {
		return paramValue;
	}

	
	@Override
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	
	@Override
	public long getVersionNo() {
		return versionNo;
	}

	
	@Override
	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
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
