package com.yihuacomputer.fish.fault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.INotifyMould;
import com.yihuacomputer.fish.api.fault.NotifyType;
import com.yihuacomputer.fish.api.fault.NotifyWay;

/**
 * 通知模板
 * 
 * @author YiHua
 *
 */
@Entity
@Table(name = "CASE_NOTIFY_MOULD")
public class NotifyMould implements INotifyMould{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_NOTIFY_MOULD")
	@SequenceGenerator(name = "SEQ_CASE_NOTIFY_MOULD", sequenceName = "SEQ_CASE_NOTIFY_MOULD")
	@Column(name = "ID")
	private long id; 
	
	@OneToOne(targetEntity = FaultClassify.class)
	@JoinColumn(name = "CLASSIFY_ID")
	private FaultClassify faultClassify;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NOTIFY_TYPE",length = 10)
	private NotifyType notifyType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NOTIFY_WAY",length = 10)
	private NotifyWay notifyWay;
	
	@Column(name = "NOTIFY_SET",length = 200)
	private String notifySet;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IFaultClassify getFaultClassify() {
		return faultClassify;
	}

	public void setFaultClassify(IFaultClassify faultClassify) {
		this.faultClassify = (FaultClassify)faultClassify;
	}

	public NotifyType getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(NotifyType notifyType) {
		this.notifyType = notifyType;
	}

	public NotifyWay getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(NotifyWay notifyWay) {
		this.notifyWay = notifyWay;
	}

	public String getNotifySet() {
		return notifySet;
	}

	public void setNotifySet(String notifySet) {
		this.notifySet = notifySet;
	}	
	
}
