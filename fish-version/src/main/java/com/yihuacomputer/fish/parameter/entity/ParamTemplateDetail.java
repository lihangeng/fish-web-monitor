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

import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ParamTemplate.class)
	@JoinColumn(name = "TEMPLATE_ID")
	private IParamTemplate paramTemplate;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ParamElement.class)
	@JoinColumn(name = "ELEMENT_ID")
	private IParamElement paramElement;

	/**
	 * @param template
	 * @param emlement
	 * @param paramValue
	 * @return
	 */
	public static ParamTemplateDetail make(IParamTemplate template,
			IParamElement emlement,String paramValue) {
		ParamTemplateDetail obj = new ParamTemplateDetail();
		obj.setParamElement(emlement);
		obj.setParamValue(paramValue);
		obj.setTemplate(template);
		return obj;
	}

	@Override
	public IParamElement getParamElement() {
		return paramElement;
	}

	@Override
	public void setParamElement(IParamElement paramElement) {
		this.paramElement = paramElement;
	}

	@Column(name = "PARAM_VALUE", length = 60)
	private String paramValue;

	public ParamTemplateDetail() {

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
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public IParamTemplate getTemplate() {
		return this.paramTemplate;
	}

	@Override
	public void setTemplate(IParamTemplate paramTemplate) {
		this.paramTemplate = paramTemplate;

	}

}
