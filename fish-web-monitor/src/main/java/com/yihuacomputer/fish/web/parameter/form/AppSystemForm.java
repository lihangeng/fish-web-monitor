package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.parameter.FileForm;
import com.yihuacomputer.fish.api.parameter.IAppSystem;

public class AppSystemForm {
	private long id;
	
	private String name;
	
	private String configName;
	
	private String configPath;
	
	private int configForm;
	
	private String remark;
	
	public AppSystemForm(){
		
	}
	
	public AppSystemForm(IAppSystem appSystem){
		this.id=appSystem.getId();
		this.name=appSystem.getName();
		this.configName=appSystem.getConfigName();
		this.configPath=appSystem.getConfigPath();
		this.configForm=appSystem.getConfigForm().getId();
		this.remark=appSystem.getRemark();
		
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

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	public int getConfigForm() {
		return configForm;
	}

	public void setConfigForm(int configForm) {
		this.configForm = configForm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void translate(IAppSystem appSystem){
		appSystem.setName(getName());
		appSystem.setConfigName(getConfigName());
		appSystem.setConfigForm(FileForm.getById(getConfigForm()));
		appSystem.setConfigPath(getConfigPath());
		appSystem.setRemark(getRemark());
	}
	
}
