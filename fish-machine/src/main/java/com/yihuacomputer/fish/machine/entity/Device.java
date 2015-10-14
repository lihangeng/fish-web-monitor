package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.CareLevel;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.PlaceType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.system.entity.User;

/**
 * 设备基本信息表
 *
 * @author pengwenchao
 */
@Entity
@Table(name = "DEV_INFO")
public class Device implements IDevice, Serializable {
	@Transient
	private IDeviceService deviceService;

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_INFO")
	@SequenceGenerator(name = "SEQ_DEV_INFO", sequenceName = "SEQ_DEV_INFO")
	@Column(name = "ID")
	private long id;

	/**
	 * 设备号(电子柜员号)
	 */
	@Column(name = "TERMINAL_ID", length = 20, unique = true)
	private String terminalId;

	/**
	 * 设备IP地址
	 */
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.IPUserType")
	@Column(name = "IP", length = 20)
	private ITypeIP ip;

	/**
	 * 所属机构
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "ORG_ID")
	private IOrganization organization;

	/**
	 * 设备型号
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = AtmType.class)
	@JoinColumn(name = "DEV_TYPE_ID")
	private IAtmType devType;

	/**
	 * 设备状态
	 */
	@Column(name = "STATUS", length = 1)
	private Status status;

	/**
	 * 设备维护商
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "DEV_SERVICE_ID")
	private IOrganization devService;

	/**
	 * 设备地址
	 */
	@Column(name = "ADDRESS", length = 50)
	private String address;

	/**
	 * 钱箱报警金额．单位：张数
	 */
	@Column(name = "CASHBOX_LIMIT")
	private int cashboxLimit;

	/**
	 * 非现金标志
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CASH_TYPE", length = 10)
	private CashType cashType;

	/**
	 * 安装方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "SETUP_TYPE", length = 10)
	private SetupType setupType;

	/**
	 * atmc软件
	 */
	@Column(name = "ATMC_APP", length = 50)
	private String atmcSoft;

	/**
	 * 厂商sp类型
	 */
	@Column(name = "ATM_SP", length = 50)
	private String sp;

	/**
	 * 在行离行标志
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "AWAY_FLAG", length = 15)
	private AwayFlag awayFlag;

	/**
	 * 经营方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "WORK_TYPE", length = 16)
	private WorkType workType;

	/**
	 * 设备关注程度
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CARE_LEVEL", length = 15)
	private CareLevel careLevel;

	/**
	 * 设备扩展
	 */
	@OneToOne(targetEntity = DeviceExtend.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_EXTEND_ID", unique = true, nullable = false)
	private IDeviceExtend deviceExtend;

	/**
	 * 虚拟柜员号
	 */
	@Column(name = "VIRTUAL", length = 25)
	private String virtual;

	/**
	 * mac地址
	 */
	@Column(name = "MAC", length = 50)
	private String mac;

	/**
	 * 是否支持视频播放
	 */
	@Column(name = "VIDEO_TYPE", length = 4)
	private String videoType;

	/**
	 * 申请人
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "APPLY_ID")
	private IUser applyId;

	/**
	 * 申请时间
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_TIME", length = 10)
	private Date applyTime;

	/**
	 * 审核人
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "AUDITER_ID")
	private IUser auditerId;

	/**
	 * 审核时间
	 */
	@Column(name = "AUD_TIME", length = 10)
	private Date audTime;

	/**
	 * 布放位置类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "PLACE_TYPE", length = 16)
	private PlaceType placeType;

	/**
	 * 申请备注
	 */
	@Column(name = "APPLY_REMARK", length = 100)
	private String applyRemark;

	/**
	 * 审核备注
	 */
	@Column(name = "AUDITER_REMARK", length = 100)
	private String auditerRemark;

	/**
	 * 审核状态
	 */
	@Column(name = "CHECK_STATUS", length = 1)
	private Status checkStatus;

	public Device() {
		this.status = Status.OPENING;
		this.cashType = CashType.CASH;
		this.careLevel = CareLevel.GENERAL;
		this.awayFlag = AwayFlag.LINE;
	}

	public Device(IDeviceService deviceService) {
		this();
		this.deviceService = deviceService;
	}

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

	public ITypeIP getIp() {
		return ip;
	}

	public void setIp(ITypeIP ip) {
		this.ip = ip;
	}

	public IOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(IOrganization organization) {
		this.organization = organization;
	}

	public IAtmType getDevType() {
		return devType;
	}

	public void setDevType(IAtmType devType) {
		this.devType = devType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public IOrganization getDevService() {
		return devService;
	}

	public void setDevService(IOrganization devService) {
		this.devService = devService;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCashboxLimit() {
		return cashboxLimit;
	}

	public void setCashboxLimit(int cashboxLimit) {
		this.cashboxLimit = cashboxLimit;
	}

	public IDeviceExtend getDeviceExtend() {
		return deviceExtend;
	}

	public void setDeviceExtend(IDeviceExtend deviceExtend) {
		this.deviceExtend = deviceExtend;
	}

	public void update(IDevice device) {
		setAddress(device.getAddress());
		setCashboxLimit(device.getCashboxLimit());
		setDeviceExtend(device.getDeviceExtend());
		setDevType(device.getDevType());
		setId(device.getId());
		setIp(device.getIp());
		setOrganization(device.getOrganization());
		setTerminalId(device.getTerminalId());
		setDevService(device.getDevService());
		setStatus(device.getStatus());
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public CashType getCashType() {
		return cashType;
	}

	public void setCashType(CashType cashType) {
		this.cashType = cashType;
	}

	public SetupType getSetupType() {
		return setupType;
	}

	public void setSetupType(SetupType setupType) {
		this.setupType = setupType;
	}

	public String getAtmcSoft() {
		return atmcSoft;
	}

	public void setAtmcSoft(String atmcSoft) {
		this.atmcSoft = atmcSoft;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public AwayFlag getAwayFlag() {
		return awayFlag;
	}

	public void setAwayFlag(AwayFlag awayFlag) {
		this.awayFlag = awayFlag;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public CareLevel getCareLevel() {
		return careLevel;
	}

	public void setCareLevel(CareLevel careLevel) {
		this.careLevel = careLevel;
	}

	public String getVirtual() {
		return virtual;
	}

	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}

	// public int getPatrolPeriod() {
	// return patrolPeriod;
	// }
	//
	// public void setPatrolPeriod(int patrolPeriod) {
	// this.patrolPeriod = patrolPeriod;
	// }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	@Override
	public IUser getApplyId() {
		return applyId;
	}

	@Override
	public void setApplyId(IUser applyId) {
		this.applyId = applyId;
	}

	@Override
	public Date getApplyTime() {
		return this.applyTime;
	}

	@Override
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Override
	public IUser getAuditerId() {
		return this.auditerId;
	}

	@Override
	public void setAuditerId(IUser auditerId) {
		this.auditerId = auditerId;
	}

	@Override
	public Date getAudTime() {
		return this.audTime;
	}

	@Override
	public void setAudTime(Date audTime) {
		this.audTime = audTime;
	}

	@Override
	public PlaceType getPlaceType() {
		return this.placeType;
	}

	@Override
	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	@Override
	public String getApplyRemark() {
		return this.applyRemark;
	}

	@Override
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	@Override
	public String getAuditerRemark() {
		return this.auditerRemark;
	}

	@Override
	public void setAuditerRemark(String auditerRemark) {
		this.auditerRemark = auditerRemark;
	}

	@Override
	public Status getCheckStatus() {
		return this.checkStatus;
	}

	@Override
	public void setCheckStatus(Status checkStatus) {
		this.checkStatus = checkStatus;
	}

}
