package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;

public class ParamPublishAppResultForm {

	private String appSystem;
	
	private String status;
	
	private String reason;

	public String getAppSystem() {
		return appSystem;
	}

	public void setAppSystem(String appSystem) {
		this.appSystem = appSystem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ParamPublishAppResultForm(IParamPublishAppResult ppar) {
		this.appSystem = ppar.getAppSystem().getName();
		if(ppar !=null){
			this.status = ppar.getStatus().getText();
		}
		this.reason = ppar.getReason();
	}

	public ParamPublishAppResultForm() {

	}
	

}
