package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;

public class DevParamClassifyForm {

	private long id;

	private String paramBelongs;

	private String paramName;

	private String paramValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParamBelongs() {
		return paramBelongs;
	}

	public void setParamBelongs(String paramBelongs) {
		this.paramBelongs = paramBelongs;
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

	public DevParamClassifyForm() {

	}

	public DevParamClassifyForm(IParamTemplateDetail paramDetail) {
		this.id = paramDetail.getId();
		this.paramName = paramDetail.getParamElement().getParamName();
		this.paramValue = paramDetail.getParamValue();
		this.paramBelongs = paramDetail.getParamElement().getParamBelongs().getName();
	}

}
