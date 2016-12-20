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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getClassifyName() {
		return classifyName;
	}

	@Override
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	@Override
	public ResponsorType getResponsorType() {
		return responsorType;
	}

	@Override
	public void setResponsorType(ResponsorType responsorType) {
		this.responsorType = responsorType;
	}

	@Override
	public double getResolveHour() {
		return resolveHour;
	}

	@Override
	public void setResolveHour(double resolveHour) {
		this.resolveHour = resolveHour;
	}

	@Override
	public int getUpgrade() {
		return upgrade;
	}

	@Override
	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	@Override
	public int getNotifyTimes() {
		return notifyTimes;
	}

	@Override
	public void setNotifyTimes(int notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	@Override
	public NotifyWay getNotifyWay() {
		return notifyWay;
	}

	@Override
	public void setNotifyWay(NotifyWay notifyWay) {
		this.notifyWay = notifyWay;
	}	
}
