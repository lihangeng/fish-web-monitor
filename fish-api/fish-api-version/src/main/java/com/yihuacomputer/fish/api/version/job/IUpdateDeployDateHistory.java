/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

import java.util.Date;

import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * @author xuxigang
 *
 */
public interface IUpdateDeployDateHistory {
    /**
     * @return
     */
    public long getId();

    /**
     * @return
     */
    public long getJobId();

    /**
     * @return
     */
    public long getTaskId();

    /**
     * @return
     */
    public Date getDeployStartDate();

    /**
     * @param deployStartDate
     */
    public void setDeployStartDate(Date deployStartDate);

    /**
     * @return
     */
    public Date getNoticeTime();

    /**
     * @param noticeTime
     */
    public void setNoticeTime(Date noticeTime);

    /**
     * @param taskId
     */
    public void setTaskId(long taskId);

    /**
     * @return
     */
    public NoticeStatus getNoticeStatus();

    /**
     * @param noticeStatus
     */
    public void setNoticeStatus(NoticeStatus noticeStatus);

    /**
     * @return
     */
    public String getReason();

    /**
     * @param reason
     */
    public void setReason(String reason);

    /**
     * @return
     */
    public ITask getTask();

    /**
     * @param task
     */
    public void setTask(ITask task);

}
