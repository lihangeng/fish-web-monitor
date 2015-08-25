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
    public long getId();

    public long getJobId();

    public long getTaskId();

    public Date getDeployStartDate();

    public void setDeployStartDate(Date deployStartDate);

    public Date getNoticeTime();

    public void setNoticeTime(Date noticeTime);

    public void setTaskId(long taskId);

    public NoticeStatus getNoticeStatus();

    public void setNoticeStatus(NoticeStatus noticeStatus);

    public String getReason();

    public void setReason(String reason);

    public ITask getTask();

    public void setTask(ITask task);

}
