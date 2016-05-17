package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;

public class ModStatusForm {
	
	private String description;
	private String solution;
	
	
	
	
	
	
	public ModStatusForm() {
	}
	public ModStatusForm(String description, String solution) {
		this.description = description;
		this.solution = solution;
	}
	
	
	
	public static ModStatusForm toForm(IStateAnalysis analysis){
		ModStatusForm form = new ModStatusForm();
		form.setDescription(analysis.getDescription());
		form.setSolution(analysis.getSolution());
		return form;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	

}
