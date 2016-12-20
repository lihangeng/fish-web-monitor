package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.person.IOrganization;

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
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
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
	private DevStatus status;

	/**
	 * 设备维护商
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "DEV_SERVICE_ID")
	private IOrganization devService;

	/**
	 * 设备地址
	 */
	@Column(name = "ADDRESS", length = 128)
	private String address;

	/**
	 * 钱箱报警金额．单位：张数
	 */
	@Column(name = "CASHBOX_LIMIT")
	private int cashboxLimit;

	/**
	 * 安装方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "SETUP_TYPE", length = 10)
	private SetupType setupType;

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
	 * 设备序列号
	 */
	@Column(name = "SERIAL", length = 40)
	private String serial;

	/**
	 * 虚拟柜员号
	 */
	@Column(name = "VIRTUAL_CODE", length = 25)
	private String virtual;

	/**
	 * mac地址
	 */
	@Column(name = "MAC", length = 50)
	private String mac;
	
	/**
	 * 网络类型
	 */
	@Column(name = "NET_TYPE", length = 2)
	private NetType netType;
	
	/**
	 * 设备安装日期 format(yyyy-MM-dd)
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "INSTALL_DATE", length = 10)
	private Date installDate;

	public Device() {
		this.status = DevStatus.OPEN;
		this.awayFlag = AwayFlag.LINE;
		this.netType = NetType.CABLE;
		this.installDate = new Date();
	}

	public Device(IDeviceService deviceService) {
		this();
		this.deviceService = deviceService;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public ITypeIP getIp() {
		return ip;
	}

	@Override
	public void setIp(ITypeIP ip) {
		this.ip = ip;
	}

	@Override
	public IOrganization getOrganization() {
		return organization;
	}

	@Override
	public void setOrganization(IOrganization organization) {
		this.organization = organization;
	}

	@Override
	public IAtmType getDevType() {
		return devType;
	}

	@Override
	public void setDevType(IAtmType devType) {
		this.devType = devType;
	}

	@Override
	public DevStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(DevStatus status) {
		this.status = status;
	}

	@Override
	public IOrganization getDevService() {
		return devService;
	}

	@Override
	public void setDevService(IOrganization devService) {
		this.devService = devService;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int getCashboxLimit() {
		return cashboxLimit;
	}

	@Override
	public void setCashboxLimit(int cashboxLimit) {
		this.cashboxLimit = cashboxLimit;
	}

	public void update(IDevice device) {
		setAddress(device.getAddress());
		setCashboxLimit(device.getCashboxLimit());
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

	@Override
	public SetupType getSetupType() {
		return setupType;
	}

	@Override
	public void setSetupType(SetupType setupType) {
		this.setupType = setupType;
	}

	@Override
	public AwayFlag getAwayFlag() {
		return awayFlag;
	}

	@Override
	public void setAwayFlag(AwayFlag awayFlag) {
		this.awayFlag = awayFlag;
	}

	@Override
	public WorkType getWorkType() {
		return workType;
	}

	@Override
	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	@Override
	public String getVirtual() {
		return virtual;
	}

	@Override
	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}

	@Override
	public String getMac() {
		return mac;
	}

	@Override
	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	public String getSerial() {
		return serial;
	}

	@Override
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public NetType getNetType() {
		return netType;
	}

	@Override
	public void setNetType(NetType netType) {
		this.netType = netType;
	}

	@Override
	public Date getInstallDate() {
		return installDate;
	}

	@Override
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

}
