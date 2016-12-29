package com.yihuacomputer.fish.web.parameter.form;

/**
 * @author YiHua
 *
 */
public class ParamTemplateParamRelationForm {
	private long id;
	private String paramId;
	private String templateId;

	public ParamTemplateParamRelationForm(){

	}
	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
