package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.system.config.IParam;

public class ParamForm {
	private long id;

	private String paramKey;

	private String paramValue;

	private String description;
	
	private int classify;

	public ParamForm() {

	}

	public ParamForm(IParam param) {
		setDescription(param.getDescription());
		setId(param.getId());
		setParamKey(param.getParamKey());
		setParamValue(param.getParamValue());
		setClassify(param.getClassify());
	}

	public static List<ParamForm> convert(List<IParam> list) {
		List<ParamForm> result = new ArrayList<ParamForm>();
		for (IParam item : list) {
			result.add(new ParamForm(item));
		}
		return result;
	}

	public void translate(IParam param) {
		param.setDescription(getDescription());
		param.setParamKey(getParamKey());
		param.setParamValue(getParamValue());
		param.setClassify(getClassify());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClassify() {
		return classify;
	}

	public void setClassify(int classify) {
		this.classify = classify;
	}

	
}
