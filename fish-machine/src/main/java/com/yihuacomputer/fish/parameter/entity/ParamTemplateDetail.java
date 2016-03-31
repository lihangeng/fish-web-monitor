package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;

/**
 * 参数模板详细信息
 *
 * @author YH
 *
 */
@Entity
@Table(name = "PARAM_TEMPLATE_DETAIL")
public class ParamTemplateDetail implements IParamTemplateDetail, Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_TEMPLATE_DETAIL")
	@SequenceGenerator(name = "SEQ_PARAM_TEMPLATE_DETAIL", sequenceName = "SEQ_PARAM_TEMPLATE_DETAIL")
	@Column(name = "ID")
	private long id;

	@Column(name = "TEMPLATE_ID")
	private long templateId;

	@Column(name = "PARAM_NAME", length = 40)
	private String paramName;

	@Column(name = "PARAM_VALUE", length = 60)
	private String paramValue;

	@Column(name = "VERSION_NO")
	private long versionNo;

	@Column(name = "PARAM_BELONGS")
	private long paramBelongs;

	public ParamTemplateDetail() {

	}

	@Override
	public String getParamName() {
		return paramName;
	}

	@Override
	public long getTemplateId() {
		return templateId;
	}

	@Override
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	@Override
	public long getParamBelongs() {
		return paramBelongs;
	}

	@Override
	public void setParamBelongs(long paramBelongs) {
		this.paramBelongs = paramBelongs;
	}

	@Override
	public void setParamName(String paramName) {
		this.paramName = paramName;
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

}
