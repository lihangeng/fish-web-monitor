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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.fault.FaultCloseType;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;

/**
 * 故障信息
 *
 * @author Yihua
 *
 */
@Entity
@Table(name = "CASE_FAULT")
public class CaseFault implements ICaseFault {

	@Transient
	private IDevicePersonRelation devicePersonService;
	@Transient
	private IDeviceService deviceService;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_FAULT")
	@SequenceGenerator(name = "SEQ_CASE_FAULT", sequenceName = "SEQ_CASE_FAULT")
	@Column(name = "ID")
	private long id;

	@Column(name = "TERMINAL_ID", length = 20)
	private String terminalId;

	@Transient
	private RunStatus appStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "DEV_MOD", length = 5)
	private DeviceMod devMod;

	@OneToOne(targetEntity = FaultClassify.class)
	@JoinColumn(name = "CLASSIFY_ID")
	private FaultClassify faultClassify;

	@Column(name = "FAULT_CODE", length = 20)
	private String faultCode;

	@Column(name = "VENDOR_HW_CODE", length = 20)
	private String vendorHwCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FAULT_TIME", length = 20)
	private Date faultTime;

	@Column(name = "FAULT_DATE")
	private Long faultDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CLOSED_TIME", length = 20)
	private Date closedTime;

	@Column(name = "DURATION")
	private double duration;

	@Enumerated(EnumType.STRING)
	@Column(name = "FAULT_STATUS", length = 10)
	private FaultStatus faultStatus;

	@Column(name = "UPGRADE")
	private int upgrade;

	@Enumerated(EnumType.STRING)
	@Column(name = "CLOSE_TYPE",length = 10)
	private FaultCloseType faultCloseType;

	@Transient
	private IOrganization org;

	@Transient
	private IDevice device;

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

	public RunStatus getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(RunStatus appStatus) {
		this.appStatus = appStatus;
	}

	public DeviceMod getDevMod() {
		return devMod;
	}

	public void setDevMod(DeviceMod devMod) {
		this.devMod = devMod;
	}

	public IFaultClassify getFaultClassify() {
		return faultClassify;
	}

	public void setFaultClassify(IFaultClassify faultClassify) {
		this.faultClassify = (FaultClassify) faultClassify;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getVendorHwCode() {
		return vendorHwCode;
	}

	public void setVendorHwCode(String vendorHwCode) {
		this.vendorHwCode = vendorHwCode;
	}

	public Date getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(Date faultTime) {
		this.faultTime = faultTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public FaultStatus getFaultStatus() {
		return faultStatus;
	}

	public void setFaultStatus(FaultStatus faultStatus) {
		this.faultStatus = faultStatus;
	}

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	public IDevice getDevice() {
		if (this.device == null) {
			this.device = deviceService.get(this.terminalId);
		}
		return this.device;
	}

	@Override
	public IOrganization getOrg() {
		if (this.org == null) {
			IDevice device = getDevice();
			if (device != null) {
				return device.getOrganization();
			}
		}
		return this.org;
	}

	@Override
	public List<IPerson> getBankPerson() {
		return this.devicePersonService.listAdminMaintainPersonByDevice(terminalId, PersonType.MANAGE);
	}

	@Override
	public List<IPerson> getServicePerson() {
		return this.devicePersonService.listAdminMaintainPersonByDevice(terminalId, PersonType.FIXMAN);
	}

	public IDevicePersonRelation getDevicePersonService() {
		return this.devicePersonService;
	}

	public void setDevicePersonService(IDevicePersonRelation devicePersonService) {
		this.devicePersonService = devicePersonService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public void setCloseType(FaultCloseType closeType) {
		this.faultCloseType = closeType ;
	}

	@Override
	public FaultCloseType getCloseType() {
		return faultCloseType;
	public Long getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Long faultDate) {
		this.faultDate = faultDate;
	}

}
