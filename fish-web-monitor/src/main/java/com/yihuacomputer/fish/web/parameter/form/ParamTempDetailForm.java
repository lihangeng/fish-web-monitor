package com.yihuacomputer.fish.web.parameter.form;


import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;

public class ParamTempDetailForm {
	private long id;
	private String paramName;
	private String paramValue;
	private long templateId;
	private String paramBelongs;

	public ParamTempDetailForm() {

	}

	public ParamTempDetailForm(IParamTemplateDetail detail) {
		setId(detail.getId());
		setTemplateId(detail.getTemplateId());
		setParamName(detail.getParamName());
		setParamValue(detail.getParamValue());
		setParamBelongs(getParamBelongs());

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

}
