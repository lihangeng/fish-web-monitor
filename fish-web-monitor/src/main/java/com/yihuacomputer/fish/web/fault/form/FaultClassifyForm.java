package com.yihuacomputer.fish.web.fault.form;

import com.yihuacomputer.fish.api.fault.IFaultClassify;

/**
 * @author YiHua
 *
 */
public class FaultClassifyForm {

	private String id;

	private String classifyName;

	private String responsorType;

	private String resolveHour;

	private int upgrade;

	private int notifyTimes;

	private String notifyWay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getResponsorType() {
		return responsorType;
	}

	public void setResponsorType(String responsorType) {
		this.responsorType = responsorType;
	}

	public String getResolveHour() {
		return resolveHour;
	}

	public void setResolveHour(String resolveHour) {
		this.resolveHour = resolveHour;
	}

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	public int getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(int notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public String getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(String notifyWay) {
		this.notifyWay = notifyWay;
	}

	public FaultClassifyForm() {
		
	}

	/**
	 * @param faultClassify
	 */
	public FaultClassifyForm(IFaultClassify faultClassify) {
		setId(faultClassify.getId());
		setClassifyName(faultClassify.getClassifyName());
		setNotifyTimes(faultClassify.getNotifyTimes());
		setResolveHour(String.valueOf(faultClassify.getResolveHour()));
		setResponsorType(faultClassify.getResponsorType() == null ? null : String.valueOf(faultClassify.getResponsorType().getId()));
		setUpgrade(faultClassify.getUpgrade());
		setNotifyWay(faultClassify.getNotifyWay() == null ? null : faultClassify.getNotifyWay().toString());
	}
}
