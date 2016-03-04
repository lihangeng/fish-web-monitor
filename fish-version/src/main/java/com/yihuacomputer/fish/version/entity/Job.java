package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.JobPriority;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.version.scheduler.Scheduler;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;

@Entity
@Table(name = "VER_JOB")
public class Job implements IJob, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_JOB")
    @SequenceGenerator(name = "SEQ_VER_JOB", sequenceName = "SEQ_VER_JOB")
    @Column(name = "ID")
    private long jobId;

    @Column(name = "JOB_NAME", nullable = true, length = 20)
    private String jobName;

    @ManyToOne(targetEntity = Version.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "VERSION_ID", nullable = true)
    private IVersion version;

    // 作业创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    // 作业计划执行时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PLAN_TIME")
    private Date planTime;

    // 作业开始执行时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;

    // 作业执行完成时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FINISH_TIME")
    private Date finishTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "JOB_TYPE", length = 20)
    private JobType jobType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "JOB_STATUS", length = 20)
    private JobStatus jobStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "JOB_PRIORITY", length = 10)
    private JobPriority jobPriority;
        
    @Column(name = "SERVER_IP")
    private String serverIp;

    @OneToMany(mappedBy = "job", targetEntity = Task.class)
    private List<ITask> tasks = new ArrayList<ITask>();

    @Column(name = "REMARK", nullable = true, length = 40)
    private String desc;

    @Transient
    private Scheduler scheduler;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEPLOY_START_DATE")
    private Date deployStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEPLOY_END_DATE")
    private Date deployEndDate;

    @Column(name = "CREATE_USER_ID", nullable = true, length = 20)
    private long createUserId;

    @Transient
    private IUser createUser;

    @Column(name = "CANCEL_PRE_VER", nullable = true, length = 20)
    private long cancelPreVer;

    @Column(name = "REBOOT_UPDATE", nullable = true, length = 20)
    private long rebootUpdate;

    public Date getDeployStartDate() {
        return deployStartDate;
    }

    public void setDeployStartDate(Date deployStartDate) {
        this.deployStartDate = deployStartDate;
    }

    public Date getDeployEndDate() {
        return deployEndDate;
    }

    public void setDeployEndDate(Date deployEndDate) {
        this.deployEndDate = deployEndDate;
    }

    @Transient
    private IDomainJobService jobService;

    public Job() {
        this.createdTime = new Date();
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public IVersion getVersion() {
        return version;
    }

    public void setVersion(IVersion version) {
        this.version = version;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date jobTime) {
        this.planTime = jobTime;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }

    public void setJobPriority(JobPriority jobPriority) {
        this.jobPriority = jobPriority;
    }

    public void setTasks(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public int getTaskSize() {
        return this.getTasks().size();
    }

    public void addTasks(List<ITask> tasks) {
        this.tasks.addAll(tasks);
    }

    public void addTask(ITask task) {
        this.tasks.add(task);
    }

    public List<ITask> getTasks() {
        try {
            return this.tasks;
        }
        catch (Exception ex) {
            return this.jobService.getTaskService().findTasksByJobId(this.jobId);
        }
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public IDomainJobService getJobService() {
        return jobService;
    }

    public void setJobService(IDomainJobService jobService) {
        this.jobService = jobService;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public IUser getCreateUser() {
        if (this.createUser == null && this.createUserId > 0) {
            this.createUser = jobService.getUserService().get(this.createUserId);
        }
        return this.createUser;
    }


    public void setCreateUser(IUser user) {
        this.createUser = user;
        if (user != null) {
            this.createUserId = user.getId();
        }
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

	public long getCancelPreVer() {
		return cancelPreVer;
	}

	public void setCancelPreVer(long cancelPreVer) {
		this.cancelPreVer = cancelPreVer;
	}

	public long getRebootUpdate() {
		return rebootUpdate;
	}

	public void setRebootUpdate(long rebootUpdate) {
		this.rebootUpdate = rebootUpdate;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	
}


