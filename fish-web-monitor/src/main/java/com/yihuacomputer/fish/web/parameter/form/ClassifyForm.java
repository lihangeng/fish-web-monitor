package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IClassify;

public class ClassifyForm {
	private long id;
	private String name;
	private String remark;

	public ClassifyForm() {

	}

	public ClassifyForm(IClassify classify) {
		setId(classify.getId());
		setName(classify.getName());
		setRemark(classify.getRemark());
	}

	public void translate(IClassify classify) {
		classify.setId(getId());
		classify.setName(getName());
		classify.setRemark(getRemark());
	}

	public static List<ClassifyForm> convert(List<IClassify> list) {
		List<ClassifyForm> result = new ArrayList<ClassifyForm>();
		for (IClassify item : list) {
			result.add(new ClassifyForm(item));
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

}
