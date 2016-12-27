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
    
    /**
     * @param taskId
     * @return
     */
    public ITaskDetail make(long taskId);
    
    /**
     * @param task
     * @return
     */
    public ITaskDetail make(ITask task);
    
    /**
     * @param taskId
     * @return
     */
    public List<ITaskDetail> findByTaskId(long taskId);

}
