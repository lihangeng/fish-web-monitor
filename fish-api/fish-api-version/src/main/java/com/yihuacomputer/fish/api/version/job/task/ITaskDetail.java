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
    
    /**
     * @return
     */
    public long getId();
    
    /**
     * @return
     */
    public long getTaskId();
    
    /**
     * @param taskId
     */
    public void setTaskId(long taskId);
    
    /**
     * @return
     */
    public Date getCreatedTime();
    
    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime);
    
    /**
     * @return
     */
    public String getTaskAction();
    
    /**
     * @param taskAction
     */
    public void setTaskAction(String taskAction);
    
    /**
     * @return
     */
    public boolean isSuccess();
    
    /**
     * @return
     */
    public String getReason();
    
    /**
     * @param reason
     */
    public void setReason(String reason);
    
}
