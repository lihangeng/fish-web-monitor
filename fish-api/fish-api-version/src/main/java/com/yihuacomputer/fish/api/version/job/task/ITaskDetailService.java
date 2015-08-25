/**
 * 
 */
package com.yihuacomputer.fish.api.version.job.task;

import java.util.List;

/**
 * 
 * 任务明细表服务
 * @since
 * @author xuxigang
 *
 */
public interface ITaskDetailService {
    
    public ITaskDetail make(long taskId);
    
    public ITaskDetail make(ITask task);
    
    public List<ITaskDetail> findByTaskId(long taskId);

}
