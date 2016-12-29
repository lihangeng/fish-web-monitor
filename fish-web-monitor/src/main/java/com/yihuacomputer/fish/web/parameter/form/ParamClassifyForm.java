package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.parameter.IParamClassify;

/**
 * @author YiHua
 *
 */
public class ParamClassifyForm {
	private long id;
	private String name;
	private String remark;

	public ParamClassifyForm() {

	}

	/**
	 * @param classify
	 */
	public ParamClassifyForm(IParamClassify classify) {
		setId(classify.getId());
		setName(classify.getName());
		setRemark(classify.getRemark());
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
