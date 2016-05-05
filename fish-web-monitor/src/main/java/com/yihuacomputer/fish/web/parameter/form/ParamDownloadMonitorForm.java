package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.parameter.IParamPublish;

public class ParamDownloadMonitorForm {
	public long id;
	
	public String date;
	
	public long publisher;
	
	private String templateName;
	
	public String publisherName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getPublisher() {
		return publisher;
	}

	public void setPublisher(long publisher) {
		this.publisher = publisher;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public ParamDownloadMonitorForm(){
		
	}
	
	public ParamDownloadMonitorForm(IParamPublish paramPublish) {
		this.id = paramPublish.getId();
		this.date = paramPublish.getDate();
		this.publisher = paramPublish.getPublisher();
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	
}
