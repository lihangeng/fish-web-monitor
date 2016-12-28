/**
 *
 */
package com.yihuacomputer.fish.version.entity;

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
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.NoticeStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * @author xuxigang
 *
 */
@Entity
@Table(name = "VER_DEPLOYDATE_HISTORY")
public class UpdateDeployDateHistory implements IUpdateDeployDateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_DEPLOYDATE_HISTORY")
    @SequenceGenerator(name = "SEQ_VER_DEPLOYDATE_HISTORY", sequenceName = "SEQ_VER_DEPLOYDATE_HISTORY")
    @Column(name = "ID")
    private long id;

    @Column(name = "TASK_ID", nullable = false)
    private long taskId;

    @Column(name = "JOB_ID", nullable = false)
    private long jobId;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTICE_STATUS", length = 15)
    private NoticeStatus noticeStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEPLOY_START_DATE")
    private Date deployStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOTICE_TIME")
    private Date noticeTime;

    @Column(name = "REASON", nullable = true, length = 256)
    private String reason;

    @Transient
    private ITask task;

    @Override
    public ITask getTask() {
        return task;
    }

    @Override
    public void setTask(ITask task) {
        this.task = task;
    }


    /**
     * 初始化
     */
    public UpdateDeployDateHistory(){
        this.noticeStatus = NoticeStatus.UNKNOW;
    }


    /**
     * @param task
     */
    public UpdateDeployDateHistory(ITask task){
        this();
        this.taskId = task.getId();
        this.jobId = task.getJob().getJobId();
    }
    
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    @Override
    public Date getDeployStartDate() {
        return deployStartDate;
    }

    @Override
    public void setDeployStartDate(Date deployStartDate) {
        this.deployStartDate = deployStartDate;
    }

    @Override
    public Date getNoticeTime() {
        return noticeTime;
    }

    @Override
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public NoticeStatus getNoticeStatus() {
        return this.noticeStatus;
    }

    @Override
    public void setNoticeStatus(NoticeStatus noticeStatus) {
       this.noticeStatus = noticeStatus;
    }

}
