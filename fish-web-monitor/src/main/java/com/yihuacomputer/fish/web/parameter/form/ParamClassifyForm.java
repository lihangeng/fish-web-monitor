package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IParamClassify;

public class ParamClassifyForm {
	private long id;
	private String name;
	private String remark;

	public ParamClassifyForm() {

	}

	public ParamClassifyForm(IParamClassify classify) {
		setId(classify.getId());
		setName(classify.getName());
		setRemark(classify.getRemark());
	}

	public void translate(IParamClassify classify) {
		classify.setId(getId());
		classify.setName(getName());
		classify.setRemark(getRemark());
	}

//	public static List<ParamClassifyForm> convert(List<IParamClassify> list) {
//		List<ParamClassifyForm> result = new ArrayList<ParamClassifyForm>();
//		for (IParamClassify item : list) {
//			result.add(new ParamClassifyForm(item));
//		}
//		return result;
//	}

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

}
