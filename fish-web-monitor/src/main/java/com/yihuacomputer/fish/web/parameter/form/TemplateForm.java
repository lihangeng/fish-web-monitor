package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.ITemplate;

public class TemplateForm {
	private long id;
	private String name;
	private String remark;

	public TemplateForm(){

	}

	public TemplateForm(ITemplate template){
		setId(template.getId());
		setName(template.getName());
		setRemark(template.getRemark());
	}

	public void translate(ITemplate template){
		template.setId(getId());
		template.setName(getName());
		template.setRemark(getRemark());
	}

	public static List<TemplateForm> convert(List<ITemplate> list) {
		List<TemplateForm> result = new ArrayList<TemplateForm>();
		for(ITemplate item:list){
			result.add(new TemplateForm(item));
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
