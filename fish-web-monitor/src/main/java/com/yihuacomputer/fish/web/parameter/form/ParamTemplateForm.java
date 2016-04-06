package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IParamTemplate;

public class ParamTemplateForm {
	private long id;
	private String name;
	private String remark;
	private long paramBelongsId;
	private String paramBelongsName;
	private String applyFlag;

	public ParamTemplateForm() {

	}

	public ParamTemplateForm(IParamTemplate template) {
		setId(template.getId());
		setName(template.getName());
		setRemark(template.getRemark());
		setParamBelongsId(template.getParamBelongs().getId());
		setParamBelongsName(template.getParamBelongs().getName());
		setApplyFlag(template.getApplyFlag());
	}

	public static List<ParamTemplateForm> convert(List<IParamTemplate> list) {
		List<ParamTemplateForm> result = new ArrayList<ParamTemplateForm>();
		for (IParamTemplate item : list) {
			result.add(new ParamTemplateForm(item));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getParamBelongsId() {
		return paramBelongsId;
	}

	public void setParamBelongsId(long paramBelongsId) {
		this.paramBelongsId = paramBelongsId;
	}

	public String getParamBelongsName() {
		return paramBelongsName;
	}

	public void setParamBelongsName(String paramBelongsName) {
		this.paramBelongsName = paramBelongsName;
	}

	public String getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
}
