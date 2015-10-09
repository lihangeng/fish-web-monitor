///**
// * 
// */
//package com.yihuacomputer.fish.version.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//
//import com.yihuacomputer.fish.api.version.IDeviceVersion;
//import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
//import com.yihuacomputer.fish.version.service.api.IDomainDeviceVersionService;
//
///**
// * 设备版本记录表 设备ID和版本ID构成唯一的一条记录 用于辅助Task类，伴随着Task而生。
// * 
// * 可以多个任务对应一个记录表
// * 
// * 解决的问题是：当有多个作业同时配置相同的设备号和版本号时，在可下发设备列表中不知道如何显显示“目标状态”的问题
// * 
// * @author xuxigang
// * 
// */
//@Entity
//@Table(name = "VER_DEVICE_VERSION")
//public class DeviceVersion implements Serializable, IDeviceVersion {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_DEVICE_VERSION")
//    @SequenceGenerator(name = "SEQ_VER_DEVICE_VERSION", sequenceName = "SEQ_VER_DEVICE_VERSION")
//    @Column(name = "ID")
//    private long id;
//
//    @Column(name = "DEVICE_ID", nullable = false)
//    private long deviceId;
//
//    @Column(name = "VERSION_ID", nullable = false)
//    private long versionId;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "CREATED_TIME", nullable = false)
//    private Date createdTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "LAST_UPDATED_TIME", nullable = false)
//    private Date lastUpdatedTime;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "TASK_STATUS", length = 20)
//    private TaskStatus taskStatus;
//
//    @Column(name = "REMARK", nullable = true, length = 40)
//    private String desc;
//
//    @Column(name = "COMPLETE_TASK_ID", nullable = false)
//    private long completeTaskId;
//
//    @Transient
//    private IDomainDeviceVersionService dvService;
//
//    public DeviceVersion() {
//        this.createdTime = new Date();
//        this.lastUpdatedTime = new Date();
//    }
//
//    @Override
//    public long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    @Override
//    public long getVersionId() {
//        return versionId;
//    }
//
//    @Override
//    public void setVersionId(long versionId) {
//        this.versionId = versionId;
//    }
//
//    @Override
//    public Date getCreatedTime() {
//        return createdTime;
//    }
//
//    @Override
//    public void setCreatedTime(Date createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    @Override
//    public Date getLastUpdatedTime() {
//        return lastUpdatedTime;
//    }
//
//    @Override
//    public void setLastUpdatedTime(Date lastUpdatedTime) {
//        this.lastUpdatedTime = lastUpdatedTime;
//    }
//
//    @Override
//    public String getDesc() {
//        return desc;
//    }
//
//    @Override
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    @Override
//    public long getDeviceId() {
//        return deviceId;
//    }
//
//    @Override
//    public void setDeviceId(long deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    @Override
//    public TaskStatus getTaskStatus() {
//        return taskStatus;
//    }
//
//    @Override
//    public void setTaskStatus(TaskStatus taskStatus) {
//        this.taskStatus = taskStatus;
//    }
//
//    @Override
//    public int getRelationTaskSize() {
//        return dvService.getRelationTaskSize(this.deviceId,this.versionId);
//    }
//
//    public long getCompleteTaskId() {
//        return completeTaskId;
//    }
//
//    public void setCompleteTaskId(long completeTaskId) {
//        this.completeTaskId = completeTaskId;
//    }
//
//}
