package com.yihuacomputer.fish.api.version.job.task;

/**
 * 任务集合
 * 
 * @author xuxiang
 * @since 2.0
 */
public interface ITaskCollection {
	
	/**
	 * 在集合中放入任务
	 * @param task
	 */
	public void put(ITask task);

	/**
	 * 在集合中取出下一个任务
	 * @return
	 */
	public ITask get();
	
	/**
	 * 将此队列中移除此任务
	 * @param task
	 * @return
	 */
	public boolean cancelTask(ITask task);
}
