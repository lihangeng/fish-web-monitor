package com.yihuacomputer.fish.version.entity;

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

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.version.service.api.IDomainTaskService;

@Entity
@Table(name = "VER_TASK")
public class Task implements ITask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_TASK")
	@SequenceGenerator(name = "SEQ_VER_TASK", sequenceName = "SEQ_VER_TASK")
	@Column(name = "ID")
	private long id;

	@Transient
	private IDevice device;

	@Column(name = "DEVICE_ID", nullable = false)
	private long deviceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_STATUS", length = 20)
	private TaskStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_TYPE", length = 15)
	private TaskType taskType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JOB_TIME")
	private Date excuteTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLAN_TIME")
	private Date planTime;

	@Column(name = "EXCUTE_MACHINE", nullable = true, length = 16)
	private String excuteMachine;

	@Column(name = "DOWN_SOURCE", nullable = true, length = 32)
	private String downSource;

	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "IS_SUCCESS", columnDefinition = "CHAR", length = 1)
	private boolean success;

	@Column(name = "REASON", nullable = true, length = 40)
	private String reason;

//	@Column(name = "JOB_NAME", nullable = true, length = 128)
//	private String jobName;
    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "JOB_ID")
    private IJob job;

	@ManyToOne(targetEntity = Version.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "VERSION_ID", nullable = false)
	private IVersion version;

	@Column(name = "VERSION_BEFORE_UPDATE", nullable = true, length = 70)
	private String versionBeforeUpdate;

	@Column(name = "EXCEPT_VERSION", nullable = true, length = 70)
	private String exceptVersion;

	/**
	 * 从２.０开始禁用
	 */
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "EAGER_RESTART", columnDefinition = "CHAR", length = 1)
	@Deprecated
	private boolean eagerRestart;

	@Transient
	private IDomainTaskService taskService;

	public Task() {
		this.status = TaskStatus.NEW;
		this.taskType = TaskType.MANUAL;
		this.eagerRestart = false;
		this.createTime = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IDevice getDevice() {
		if (this.device == null && this.deviceId > 0) {
			this.device = this.getTaskService().getDeviceService().get(this.deviceId);
		}
		return device;
	}

	public void setDevice(IDevice device) {
		this.device = device;
		if (device != null) {
			this.deviceId = device.getId();
		}
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
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

	public String toString() {
		return this.getDevice().getTerminalId() + " ~ " + this.getDevice().getIp().toString();
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public IDomainTaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(IDomainTaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public String getState() {
		if (this.getStatus().equals(TaskStatus.NEW) || this.getStatus().equals(TaskStatus.RUN)) {
			return this.getStatus().getText();
		} else {
			return this.getStatus().getText() + (this.isSuccess() ? "(成功)" : "(失败)");
		}
	}

	@Override
	public boolean isNoticedSuccess() {
		if (this.status.equals(TaskStatus.NOTICED) && this.isSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDownloadSuccess() {
		if (this.status.equals(TaskStatus.DOWNLOADED) && this.isSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDeploySuccess() {
		if (this.status.equals(TaskStatus.DEPLOYED) && this.isSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public IVersion getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(IVersion version) {
		this.version = version;
	}

	public String getVersionBeforeUpdate() {
		return versionBeforeUpdate;
	}

	public void setVersionBeforeUpdate(String versionBeforeUpdate) {
		this.versionBeforeUpdate = versionBeforeUpdate;
	}

	public String getExceptVersion() {
		return exceptVersion;
	}

	public void setExceptVersion(String exceptVersion) {
		this.exceptVersion = exceptVersion;
	}

	public boolean isEagerRestart() {
		return eagerRestart;
	}

	public void setEagerRestart(boolean eagerRestart) {
		this.eagerRestart = eagerRestart;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@Override
	public Date getDeployStartDate() {
		return new Date();
		// TODO : return this.getJob().getDeployStartDate();
	}

	@Override
	public Date getDeployEndDate() {
		return new Date();
		// TODO :return this.getJob().getDeployEndDate();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExcuteMachine() {
		return excuteMachine;
	}

	public void setExcuteMachine(String excuteMachine) {
		this.excuteMachine = excuteMachine;
	}

	public String getDownSource() {
		return downSource;
	}

	public void setDownSource(String downSource) {
		this.downSource = downSource;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public IJob getJob() {
		return this.job;
	}

	public void setJob(IJob job){
		this.job = job;
	}

	public String getJobName() {
		return this.getJob().getJobName();
	}

}
