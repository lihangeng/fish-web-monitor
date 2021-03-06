/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceSoftVersionService;

/**
 * @author xuxigang
 * 
 */
@Entity
@Table(name = "VER_DEVICE_SOFT_VERSION")
public class DeviceSoftVersion implements IDeviceSoftVersion, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_DEVICE_SOFT_VERSION")
	@SequenceGenerator(name = "SEQ_VER_DEVICE_SOFT_VERSION", sequenceName = "SEQ_VER_DEVICE_SOFT_VERSION")
	@Column(name = "ID")
	private long id;

	@Transient
	private IDevice device;

    @ManyToOne(targetEntity = VersionType.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "VERSION_TYPE_ID", nullable = false)
	private IVersionType versionType;

	@Column(name = "TERMINAL_ID", nullable = false, updatable = false,length = 20)
	private String terminalId;

	@Transient
	private IVersion version;

	@Column(name = "TYPE_NAME", nullable = false, length = 40)
	private String typeName;

	@Column(name = "VERSION_NO", nullable = false, length = 40)
	private String versionNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_TIME", nullable = false)
	private Date lastUpdatedTime;
	
    /**
     * 版本号字符串拼接
     * 1.2.3.4* 00000001000000020000000300000004
     * 1.1.32.5*00000001000000020000003200000005
     */
    @Column(name = "VERSION_STR", nullable = false, length = 40)
    private String versionStr;


	@Column(name = "REMARK", nullable = true, length = 40)
	private String desc;

	@Transient
	private IDomainDeviceSoftVersionService dsvService;

	/**
	 * 初始化
	 */
	public DeviceSoftVersion() {
		this.createdTime = new Date();
		this.lastUpdatedTime = new Date();
	}

	/**
	 * @param dsvService
	 */
	public DeviceSoftVersion(IDomainDeviceSoftVersionService dsvService) {
		this();
		this.dsvService = dsvService;
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
	public IDevice getDevice() {
		return device;
	}

	@Override
	public IVersion getVersion() {
		if (this.version == null) {
			this.version = dsvService.findVersion(this.typeName, this.versionNo);
		}
		return this.version;
	}

	@Override
	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	@Override
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getTypeName() {
		return typeName;
	}

	@Override
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String getVersionNo() {
		return versionNo;
	}

	@Override
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public void setDevice(IDevice device) {
		this.device = device;
	}

	public IDomainDeviceSoftVersionService getDsvService() {
		return dsvService;
	}

	public void setDsvService(IDomainDeviceSoftVersionService dsvService) {
		this.dsvService = dsvService;
	}

	@Override
	public String getVersionStr() {
		return versionStr;
	}

	@Override
	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

	@Override
	public IVersionType getVersionType() {
		return versionType;
	}

	@Override
	public void setVersionType(IVersionType versionType) {
		this.versionType = versionType;
	}

}
