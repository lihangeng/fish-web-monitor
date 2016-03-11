package com.yihuacomputer.fish.version.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

public class TaskManager implements Runnable {

	private Logger logger = LoggerFactory.getLogger(TaskManager.class);

	private ITaskService taskService;

	private TaskThreadPool taskThreadPool;
	
	private TaskQueue taskQueue;
	
	private MessageSource messageSource;

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService,TaskThreadPool taskThreadPool,TaskQueue taskQueue,MessageSource messageSource) {
		this.taskService = taskService;
		this.taskThreadPool = taskThreadPool;
		this.taskQueue = taskQueue;
		this.messageSource = messageSource;
	}
	
	public TaskQueue getTaskQueue() {
		return taskQueue;
	}


	/**
	 * 从任务队列中获取需要执行的任务，并且执行
	 * */
	public void run() {
		while (true) {
			try {
				ITask task = this.taskQueue.getTaskQueue().take();
				//取出对列的时候再次校验：没有下载成功,没有通知成功，新建的任务放入任务队列
    			if(TaskStatus.canRun(task.getStatus())){
    				task.setStatus(TaskStatus.RUN);
    				TaskThread taskThread = new TaskThread(task);
    				taskThread.setTaskService(taskService);
    				taskThread.setMessageSource(messageSource);
    				taskService.updateTaskStatus(task);
    				this.taskThreadPool.getTaskExecutor().execute(taskThread);
				}
			} catch (Exception e) {
				logger.error(String.format("Task_Manager Thread error！[%s]",e));
			}
		}
	}
}
