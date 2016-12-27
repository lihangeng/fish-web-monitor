package com.yihuacomputer.fish.api.version.job.task;

import java.util.List;

/**
 * 
 * 任务管理器
 * 通过页面创建的批量任务或者自动更新生成的任务统一由ITaskManager来管理
 * 该类是版本分发过程中的统一入口
 * @author xuxiang
 * @since 2.0.0.0
 *
 */
public interface ITaskManager {
	
	/**
	 * 通过页面创建的批量任务
	 * @param tasks
	 */
	public void createTasksByWeb(List<ITask> tasks);
	
	/**
	 * 通过自动更新或者系统生成的任务
	 * @param tasks
	 */
	public void createTasksBySystem(List<ITask> tasks);
	
	/**
     * 取消一个任务
     * @param taskId 任务ID
     */
    public void cancelTask(long taskId);
    
    /**
	 * 取消同一个批次的任务
	 * @param list
	 * */
	public void cancelTasks(List<ITask> list);

	/**
	 * @param task
	 */
	public void createTasksByWeb(ITask task);
	/**
	 * 加载系统异常终止的任务
	 * @return
	 * */
	public void loadUnFinishedTasks();
	


}
