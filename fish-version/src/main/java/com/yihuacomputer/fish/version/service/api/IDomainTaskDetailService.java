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
    
    ITaskDetail add(ITaskDetail td);

    public void deleteByTaskId(long taskId);
}
