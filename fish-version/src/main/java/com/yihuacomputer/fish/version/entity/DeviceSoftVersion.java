/**
 * 
 */
package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IVersion;
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

	@Column(name = "TERMINAL_ID", nullable = false, updatable = false,length = 20)
	private String terminalId;

	@Transient
	private IVersion version;

	@Column(name = "TYPE_NAME", nullable = false, length = 40)
	private String typeName;

	@Column(name = "VERSION_NO", nullable = false, length = 40)
	private String versionNo;

    /**
     * 版本号字符串拼接
     * 1.2.3.4* 00000001000000020000000300000004
     * 1.1.32.5*00000001000000020000003200000005
     */
    @Column(name = "VERSION_STR", nullable = false, length = 40)
    private String versionStr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_TIME", nullable = false)
	private Date lastUpdatedTime;

	@Column(name = "REMARK", nullable = true, length = 40)
	private String desc;

	@Transient
	private IDomainDeviceSoftVersionService dsvService;

	public DeviceSoftVersion() {
		this.createdTime = new Date();
		this.lastUpdatedTime = new Date();
	}

	public DeviceSoftVersion(IDomainDeviceSoftVersionService dsvService) {
		this();
		this.dsvService = dsvService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IDevice getDevice() {
		// TODO
		return device;
	}

	public IVersion getVersion() {
		if (this.version == null) {
			this.version = dsvService.findVersion(this.typeName, this.versionNo);
		}
		return this.version;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getVersionNo() {
		return versionNo;
	}

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

	public String getVersionStr() {
		return versionStr;
	}

	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

}
