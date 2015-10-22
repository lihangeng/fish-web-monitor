package com.yihuacomputer.fish.machine.entity;

import java.util.Date;

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

import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.device.StopType;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;

@Entity
@Table(name = "DEV_STOP")
public class QuittingNotice implements IQuittingNotice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_STOP")
	@SequenceGenerator(name = "SEQ_DEV_STOP", sequenceName = "SEQ_DEV_STOP")
	@Column(name = "ID")
	private long id;
	/**
	 * 设备编号
	 */
	@Column(name = "DEV_NO", length = 20)
	private String deviceCode;
	/**
	 * 停机时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STOP_TIME", length = 20)
	private Date stopTime;
	/**
	 * 恢复时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPEN_TIME", length = 20)
	private Date openTime;
	/**
	 * 停机类型
	 */
	@Enumerated(EnumType.ORDINAL)//存放枚举类型ID号
	@Column(name = "STOP_TYPE")
	private StopType stopType;
	/**
	 * 停机原因
	 */
	@Column(name = "STOP_REASION", length = 60)
	private String stopReason;
	/**
	 * 设置时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SET_TIME", length = 20)
	private Date setTime;
	/**
	 * 停机责任人姓名
	 */
	@Column(name = "RESP_NAME", length = 20)
	private String responsibilityName;
	
	/**
	 * 停用状态
	 */
	@Enumerated(EnumType.STRING)//存放枚举类型ID号
	@Column(name = "DEV_STATUS", length = 10)
	private Status devStatus;

	public QuittingNotice(){}
	
	public Status getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(Status devStatus) {
		this.devStatus = devStatus;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Override
	public String getDeviceCode() {
		return this.deviceCode;
	}

	@Override
	public void setResponsiblilityName(String responsibilityName) {
		this.responsibilityName = responsibilityName;
	}

	@Override
	public String getResponsibilityName() {
		return this.responsibilityName;
	}

	public void update(IQuittingNotice quittingNotice) {
		setDeviceCode(quittingNotice.getDeviceCode());
		setOpenTime(quittingNotice.getOpenTime());
		setResponsiblilityName(quittingNotice.getResponsibilityName());
		setSetTime(quittingNotice.getSetTime());
		setStopReason(quittingNotice.getStopReason());
		setStopTime(quittingNotice.getStopTime());
		setStopType(quittingNotice.getStopType());
	}

	@Override
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	@Override
	public Date getStopTime() {
		return stopTime;
	}

	@Override
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	@Override
	public Date getOpenTime() {
		return openTime;
	}

	@Override
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	@Override
	public String getStopReason() {
		return stopReason;
	}

	@Override
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	@Override
	public Date getSetTime() {
		return setTime;
	}

	@Override
	public void setStopType(StopType stopType) {
		this.stopType = stopType;

	}

	@Override
	public StopType getStopType() {
		return stopType;
	}

}
