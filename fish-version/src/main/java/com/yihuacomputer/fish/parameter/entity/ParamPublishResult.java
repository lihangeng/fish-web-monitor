package com.yihuacomputer.fish.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
@Entity
@Table(name="PARAM_PUBLISH_RESULT")
public class ParamPublishResult implements IParamPublishResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_PUBLISH_RESULT")
	@SequenceGenerator(name = "SEQ_PARAM_PUBLISH_RESULT", sequenceName = "SEQ_PARAM_PUBLISH_RESULT")
	@Column(name = "ID")
	private long id;

    @ManyToOne(targetEntity = ParamPublish.class)
    @JoinColumn(name = "PARAM_PUBLISH_ID")
	private IParamPublish paramPublish;
    
    @Column(name = "DEVICE_ID")
	private long deviceId;
    @Transient
    private IDevice device;
    
    @Column(name = "VERSION_NO")
	private long versionNo;
    
    @Column(name = "DOWNLOAD_START_TIME")
    private String downloadStartTime ;

    @Column(name = "DOWNLOAD_FINISH_TIME")
    private String downloadFinishTime ;
    
    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "IS_SUCCESS", columnDefinition = "CHAR", length = 1)
    private boolean success;

    @Column(name = "REASON", nullable = true, length = 120)
    private String reason;

	@Enumerated(EnumType.STRING)
    @Column(name = "RET", nullable = true, length = 20)
	private TaskStatus ret;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IParamPublish getParamPublish() {
		return paramPublish;
	}

	public void setParamPublish(IParamPublish paramPublish) {
		this.paramPublish = paramPublish;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}

	public String getDownloadStartTime() {
		return downloadStartTime;
	}

	public void setDownloadStartTime(String downloadStartTime) {
		this.downloadStartTime = downloadStartTime;
	}

	public String getDownloadFinishTime() {
		return downloadFinishTime;
	}

	public void setDownloadFinishTime(String downloadFinishTime) {
		this.downloadFinishTime = downloadFinishTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public TaskStatus getRet() {
		return ret;
	}

	public void setRet(TaskStatus ret) {
		this.ret = ret;
	}

	public IDevice getDevice() {
		return device;
	}

	public void setDevice(IDevice device) {
		this.device = device;
	}
	
}
