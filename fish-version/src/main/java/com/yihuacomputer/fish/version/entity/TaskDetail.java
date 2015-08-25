package com.yihuacomputer.fish.version.entity;

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

import com.yihuacomputer.fish.api.version.job.task.ITaskDetail;

/**
 * 任务详情记录表
 * 记录任务在每个执行阶段的执行时间等信息
 * @since 0.17
 * @author xuxigang
 * 
 */
@Entity
@Table(name = "VER_TASK_DETAIL")
public class TaskDetail implements ITaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_TASK_DETAIL")
    @SequenceGenerator(name = "SEQ_VER_TASK_DETAIL", sequenceName = "SEQ_VER_TASK_DETAIL")
    @Column(name = "ID")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "TASK_ID", nullable = false)
    private long taskId;

    @Column(name = "TASK_ACTION", length = 20)
    private String taskAction;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "IS_SUCCESS", columnDefinition = "CHAR", length = 1)
    private boolean success;

    @Column(name = "REMARK", nullable = true, length = 40)
    private String reason;
    
    public TaskDetail(){
        this.createdTime = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {
        this.taskAction = taskAction;
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
        if(reason != null &&  !"".equals(reason)){
            this.success = false;
        }
        this.reason = reason;
    }

}
