/**
 * 
 */
package com.yihuacomputer.fish.version.service.api;

import com.yihuacomputer.fish.api.version.job.task.ITaskDetail;
import com.yihuacomputer.fish.api.version.job.task.ITaskDetailService;

/**
 * @author xuxigang
 * 
 */
public interface IDomainTaskDetailService extends ITaskDetailService {
    
    /**
     * @param td
     * @return
     */
    ITaskDetail add(ITaskDetail td);

    /**
     * @param taskId
     */
    public void deleteByTaskId(long taskId);
}
