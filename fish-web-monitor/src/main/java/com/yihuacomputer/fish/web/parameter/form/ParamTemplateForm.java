package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IParamTemplate;

public class ParamTemplateForm {
	private long id;
	private String name;
	private String remark;

	public ParamTemplateForm(){

	}

	public ParamTemplateForm(IParamTemplate template){
		setId(template.getId());
		setName(template.getName());
		setRemark(template.getRemark());
	}

	public void translate(IParamTemplate template){
		template.setId(getId());
		template.setName(getName());
		template.setRemark(getRemark());
	}

	public static List<ParamTemplateForm> convert(List<IParamTemplate> list) {
		List<ParamTemplateForm> result = new ArrayList<ParamTemplateForm>();
		for(IParamTemplate item:list){
			result.add(new ParamTemplateForm(item));
		}
		return result;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}




	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
}
