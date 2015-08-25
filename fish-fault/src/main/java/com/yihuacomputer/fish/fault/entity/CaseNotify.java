package com.yihuacomputer.fish.fault.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.fault.ICaseNotify;
import com.yihuacomputer.fish.api.fault.NotifyWay;
import com.yihuacomputer.fish.api.person.IPerson;

/**
 * 设备故障、工单通知
 *
 * @author YiHua
 *
 */
@Entity
@Table(name = "CASE_NOTIFY")
public class CaseNotify implements ICaseNotify {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_NOTIFY")
	@SequenceGenerator(name = "SEQ_CASE_NOTIFY", sequenceName = "SEQ_CASE_NOTIFY")
	@Column(name = "ID")
	private long id;

	@Column(name = "TERMINAL_ID",length = 20)
	private String terminalId;

	@Column(name = "FAULT_ID")
	private long faultId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME",length = 20)
	private Date createTime;

	@Column(name = "CONTENT",length = 1024,nullable=false)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "NOTIFY_WAY",length = 10)
	private NotifyWay notifyWay;

	@Column(name = "MOBILE",length = 20)
	private String mobile;

	@Column(name = "MAIL",length = 40)
	private String mail;

	@Column(name = "NOTIFY_TIMES")
	private int notifyTimes;

	@Column(name = "SEND_TIMES")
	private int sendTimes;

	@Column(name = "SEND_INTERVAL")
	private int sendInterval;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME",length = 20)
	private Date sendTime;

	@Column(name = "SEND_PERSON")
	private String sendPerson;

	@Transient
	private List<IPerson> bankPerson;

	@Transient
	private List<IPerson> serPerson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public long getFaultId() {
		return faultId;
	}

	public void setFaultId(long faultId) {
		this.faultId = faultId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotifyWay getNotifyWay() {
		return notifyWay;
	}

	public void setNotifyWay(NotifyWay notifyWay) {
		this.notifyWay = notifyWay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(int notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public int getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(int sendTimes) {
		this.sendTimes = sendTimes;
	}

	public int getSendInterval() {
		return sendInterval;
	}

	public void setSendInterval(int sendInterval) {
		this.sendInterval = sendInterval;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public List<IPerson> getBankPerson() {
		return this.bankPerson;
	}

	@Override
	public void setBankPerson(List<IPerson> list) {
		this.bankPerson = list;
	}

	@Override
	public  List<IPerson> getSerPerson() {
		return this.serPerson;
	}

	@Override
	public void setSerPerson(List<IPerson> list) {
		this.serPerson = list;
	}

	@Override
	public String getSendPerson() {
		return this.sendPerson;
	}

	@Override
	public void setSendPerson(String personName) {
		this.sendPerson = personName;
	}
}
