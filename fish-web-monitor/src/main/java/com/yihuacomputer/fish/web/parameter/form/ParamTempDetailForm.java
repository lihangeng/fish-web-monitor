package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;

public class ParamTempDetailForm {
	private long id;
	private String paramName;
	private String paramValue;
	private long templateId;
	private String paramBelongs;
	private long elementId;
	private String resources;

	public ParamTempDetailForm() {

	}

	public ParamTempDetailForm(IParamTemplateDetail detail) {
		setId(detail.getId());
		setTemplateId(detail.getTemplateId());
		setParamName(detail.getParamElement().getParamName());
		setParamValue(detail.getParamValue());
		setParamBelongs(detail.getParamElement().getParamBelongs());
		setElementId(detail.getParamElement().getId());

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

}
