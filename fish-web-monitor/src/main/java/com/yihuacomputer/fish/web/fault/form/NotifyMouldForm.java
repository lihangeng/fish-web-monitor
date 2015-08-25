package com.yihuacomputer.fish.web.fault.form;

import com.yihuacomputer.fish.api.fault.INotifyMould;

public class NotifyMouldForm {

	private long id;

	private String notifyType;

	private String notifyWay;

	private String notifySet;

	private String faultClassifyId;

	private String classifyName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(String notifyWay) {
		this.notifyWay = notifyWay;
	}

	public String getNotifySet() {
		return notifySet;
	}

	public void setNotifySet(String notifySet) {
		this.notifySet = notifySet;
	}

	public NotifyMouldForm() {

	}

	public String getFaultClassifyId() {
		return faultClassifyId;
	}

	public void setFaultClassifyId(String faultClassifyId) {
		this.faultClassifyId = faultClassifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public NotifyMouldForm(INotifyMould notifyMould) {
		setId(notifyMould.getId());
		setNotifyType(notifyMould.getNotifyType() == null ? null : String.valueOf(notifyMould.getNotifyType().getId()));
		setNotifyWay(notifyMould.getNotifyWay() == null ? null : notifyMould.getNotifyWay().toString());
		setNotifySet(notifyMould.getNotifySet());
		setFaultClassifyId(notifyMould.getFaultClassify().getId());
		setClassifyName(notifyMould.getFaultClassify().getClassifyName());
	}

}
