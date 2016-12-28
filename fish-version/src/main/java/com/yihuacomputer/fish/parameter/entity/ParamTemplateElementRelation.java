package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IParamTemplateElementRelation;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "PARAM_TEMPLATE_EMLEMT_RELATION")
public class ParamTemplateElementRelation implements
		IParamTemplateElementRelation, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PAR_TEMPLATE_EMLEMT_RELATION")
	@SequenceGenerator(name = "SEQ_PAR_TEMPLATE_EMLEMT_RELATION", sequenceName = "SEQ_PAR_TEMPLATE_EMLEMT_RELATION")
	@Column(name = "ID", length = 20)
	private long id;
	/**
	 * 模板ID
	 */
	@Column(name = "TEMPLATE_ID", length = 20)
	private long templateId;

	/**
	 * 元数据ID
	 */
	@Column(name = "ELEMENT_ID", length = 20)
	private long elementId;

	public ParamTemplateElementRelation() {

	}

	/**
	 * 
	 * @param templateId
	 * @param elementId
	 * @return
	 */
	public static ParamTemplateElementRelation make(Long templateId,
			Long elementId) {
		ParamTemplateElementRelation obj = new ParamTemplateElementRelation();
		obj.templateId = templateId;
		obj.elementId = elementId;
		return obj;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	@Override
	public long getElementId() {
		return elementId;
	}

	public void setElementId(long elementId) {
		this.elementId = elementId;
	}


}
