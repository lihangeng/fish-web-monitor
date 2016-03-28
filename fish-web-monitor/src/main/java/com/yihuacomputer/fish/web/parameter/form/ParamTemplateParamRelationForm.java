package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IParamTemplate;

public class ParamTemplateParamRelationForm {
	private long id;
	private String name;
	private String remark;

	public ParamTemplateParamRelationForm(){

	}

	public ParamTemplateParamRelationForm(IParamTemplate template){
		setId(template.getId());
		setName(template.getName());
		setRemark(template.getRemark());
	}

	public void translate(IParamTemplate template){
		template.setId(getId());
		template.setName(getName());
		template.setRemark(getRemark());
	}

	public static List<ParamTemplateParamRelationForm> convert(List<IParamTemplate> list) {
		List<ParamTemplateParamRelationForm> result = new ArrayList<ParamTemplateParamRelationForm>();
		for(IParamTemplate item:list){
			result.add(new ParamTemplateParamRelationForm(item));
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
