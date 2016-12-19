package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;

public class ParamTempDetailForm {
	
	private static Logger logger = LoggerFactory.getLogger(ParamTempDetailForm.class);
	
	private long id;
	private String name;
	private String remark;
	private String paramName;
	private String paramValue;
	private long templateId;
	private String templateName;
	private String paramBelongs;
	private long elementId;
	private String resources;

	public ParamTempDetailForm() {

	}

	public ParamTempDetailForm(IParamTemplateDetail detail) {
		setId(detail.getId());
		setTemplateId(detail.getTemplate().getId());
		setParamName(detail.getParamElement().getParamName());
		setParamValue(detail.getParamValue());
		setParamBelongs(detail.getParamElement().getParamBelongs().getName());
		setElementId(detail.getParamElement().getId());
		setTemplateName(detail.getTemplate().getName());
		setName(detail.getTemplate().getName());
		setRemark(detail.getTemplate().getRemark());
	}


	public List<ParamTempDetailForm> getParamTempDetailForm() {
		List<ParamTempDetailForm> tempDetailForm = new ArrayList<ParamTempDetailForm>();
		if (StringUtils.isNotEmpty(this.resources)) {
			try {
				JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,
						true);
				tempDetailForm = JsonUtils.om.readValue(this.resources,
						new TypeReference<List<ParamTempDetailForm>>() {
						});
			} catch (Exception e) {
				logger.error(String.format("[%s]", e));
			}
		}
		return tempDetailForm;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public String getParamBelongs() {
		return paramBelongs;
	}

	public void setParamBelongs(String paramBelongs) {
		this.paramBelongs = paramBelongs;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public long getElementId() {
		return elementId;
	}

	public void setElementId(long elementId) {
		this.elementId = elementId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
