package com.yihuacomputer.fish.api.parameter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;

public class DeviceParam {
	
	private long id;

	private long paramClassifyId;
	
	private String paramClassify;
	
	private String paramName;

	private String elementParamValue;
	
	private String templateParamValue;
	
	private long deviceParamId;
	
	private String paramValue;
	
	private String data;

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

	public String getElementParamValue() {
		return elementParamValue;
	}

	public void setElementParamValue(String elementParamValue) {
		this.elementParamValue = elementParamValue;
	}

	public String getTemplateParamValue() {
		return templateParamValue;
	}

	public void setTemplateParamValue(String templateParamValue) {
		this.templateParamValue = templateParamValue;
	}
	
	public long getDeviceParamId() {
		return deviceParamId;
	}

	public void setDeviceParamId(long deviceParamId) {
		this.deviceParamId = deviceParamId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	public List<DeviceParam> getDeviceParam() {
		List<DeviceParam> tempDetailForm = new ArrayList<DeviceParam>();
		if (StringUtils.isNotEmpty(this.data)) {
			try {
				JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,
						true);
				tempDetailForm = JsonUtils.om.readValue(this.data,
						new TypeReference<List<DeviceParam>>() {
						});
			} catch (Exception e) {
			}
		}
		return tempDetailForm;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
}
