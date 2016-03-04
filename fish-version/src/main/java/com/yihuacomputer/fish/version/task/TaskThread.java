/**
 *
 */
package com.yihuacomputer.fish.version.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;

/**
 * @author xuxigang
 *
 */
public class TaskThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(TaskThread.class);

	private ITask task;
	private ITaskService taskService;

	public TaskThread() {
	}

	public TaskThread(ITask task) {
		this.task = task;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public ITask getTask() {
		return task;
	}

	public void setTask(ITask task) {
		this.task = task;
	}

	/**
	 * 具体的执行任务
	 * */
	public synchronized void run() {
		logger.info("Starting notify atm client：" + task.toString()+task.getState());
		if(!taskService.noticeATM(task)){
			// 尝试等待20秒
	        synchronized (this) {
	            try {
	                this.wait(20 * 1000);
	            }
	            catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			logger.info(task.toString() + "notify finish!");
		}else{
			logger.info(task.toString() + "ignore");
		}
	}

}
