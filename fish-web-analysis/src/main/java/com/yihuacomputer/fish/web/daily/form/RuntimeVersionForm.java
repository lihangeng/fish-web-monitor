package com.yihuacomputer.fish.web.daily.form;
public class RuntimeVersionForm {

	/**
	 * 软件名称
	 */
	private String name;
	/**
	 * 软件版本
	 */
	private String version;
	
	

	public RuntimeVersionForm() {
	}

	public RuntimeVersionForm(RuntimeVersionForm form) {
		setName(form.getName());
		setVersion(form.getVersion());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
