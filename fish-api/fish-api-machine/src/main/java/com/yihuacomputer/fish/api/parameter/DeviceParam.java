package com.yihuacomputer.fish.api.parameter;

public class DeviceParam {
	
	private long id;

	private long paramClassifyId;
	
	private String paramClassify;
	
	private String paramName;

	private String paramValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getParamClassifyId() {
		return paramClassifyId;
	}

	public void setParamClassifyId(long paramClassifyId) {
		this.paramClassifyId = paramClassifyId;
	}

	public String getParamClassify() {
		return paramClassify;
	}

	public void setParamClassify(String paramClassifyName) {
		this.paramClassify = paramClassifyName;
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
}
