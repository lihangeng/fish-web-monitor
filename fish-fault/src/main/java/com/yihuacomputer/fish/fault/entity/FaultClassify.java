package com.yihuacomputer.fish.fault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.fault.ResponsorType;

/**
 * 设备故障、工单分类
 * 
 * @author YiHua
 *
 */
@Entity
@Table(name = "CASE_FAULT_CLASSIFY")
public class FaultClassify implements IFaultClassify{

	@Id
	@Column(name = "ID",length=10,nullable=false)
	private String id;
	
	@Column(name = "CLASSIFY_NAME",length=30,nullable=false)
	private String classifyName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RESPONSOR_TYPE",length=10,nullable=false)
	private ResponsorType responsorType;
	
	@Column(name = "RESOLVE_HOUR",nullable=false)
	private double resolveHour;
	
	@Column(name = "UPGREAD",nullable=false)
	private int upgrade;
	
	@Column(name = "NOTIFY_TIMES",nullable=false)
	private int notifyTimes;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NOTIFY_WAY",nullable=false)
	private NotifyWay notifyWay;

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

	public ResponsorType getResponsorType() {
		return responsorType;
	}

	public void setResponsorType(ResponsorType responsorType) {
		this.responsorType = responsorType;
	}

	public double getResolveHour() {
		return resolveHour;
	}

	public void setResolveHour(double resolveHour) {
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

	public NotifyWay getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(NotifyWay notifyWay) {
		this.notifyWay = notifyWay;
	}	
}
