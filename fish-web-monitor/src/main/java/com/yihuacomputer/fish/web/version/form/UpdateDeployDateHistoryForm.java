package com.yihuacomputer.fish.web.version.form;

import java.util.Date;

import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.NoticeStatus;

/**
 * @author YiHua
 *
 */
public class UpdateDeployDateHistoryForm {
    private long id;

    /**
     * 任务号
     */
    private long taskId;

    /**
     * 工作号
     */
    private long jobId;

    /**
     * 通知状态
     */
    private NoticeStatus noticeStatus;

    /**
     * 部署开始日期
     */
    private Date deployStartDate;

    /**
     * 通知时间
     */
    private Date noticeTime;

    /**
     * 原因
     */
    private String reason;

    /**
     * 设备号
     */
    private String terminalId;

    /**
     * 分组
     */
    private String groupName;

    public UpdateDeployDateHistoryForm() {

    }

    /**
     * @param form
     */
    public UpdateDeployDateHistoryForm(IUpdateDeployDateHistory form) {
        setId(form.getId());
        setJobId(form.getJobId());
        setDeployStartDate(form.getDeployStartDate());
        setNoticeStatus(form.getNoticeStatus());
        setNoticeTime(form.getNoticeTime());
        setReason(form.getReason());
        setTerminalId(form.getTask().getDevice().getTerminalId());
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public NoticeStatus getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(NoticeStatus noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Date getDeployStartDate() {
        return deployStartDate;
    }

    public void setDeployStartDate(Date deployStartDate) {
        this.deployStartDate = deployStartDate;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
