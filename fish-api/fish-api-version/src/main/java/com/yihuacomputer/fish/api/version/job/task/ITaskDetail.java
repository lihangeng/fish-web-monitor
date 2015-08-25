/**
 * 
 */
package com.yihuacomputer.fish.api.version.job.task;

import java.util.Date;

/**
 * 任务明细表
 * @since 0.17
 * @author xuxigang
 *
 */
public interface ITaskDetail {
    
    public long getId();
    
    public long getTaskId();
    
    public void setTaskId(long taskId);
    
    public Date getCreatedTime();
    
    public void setCreatedTime(Date createdTime);
    
    public String getTaskAction();
    
    public void setTaskAction(String taskAction);
    
    public boolean isSuccess();
    
    public String getReason();
    
    public void setReason(String reason);
    
}
