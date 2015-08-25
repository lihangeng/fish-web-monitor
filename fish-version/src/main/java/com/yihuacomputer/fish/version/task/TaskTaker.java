package com.yihuacomputer.fish.version.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskCollection;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

public class TaskTaker implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(TaskTaker.class);
	
	private ITaskService taskService;
	
	private ITaskCollection taskCollection;
	
	private TaskThreadPool taskThreadPool;
	
	public TaskTaker(ITaskService taskService,ITaskCollection taskCollection,TaskThreadPool taskThreadPool){
		this.taskService = taskService;
		this.taskCollection = taskCollection;
		this.taskThreadPool = taskThreadPool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ITask task = this.taskCollection.get();
				//取出对列的时候再次校验：没有下载成功,没有通知成功，新建的任务放入任务队列
    			if(task != null && TaskStatus.canRun(task.getStatus())){
    				task.setStatus(TaskStatus.RUN);
    				TaskThread taskThread = new TaskThread(task);
    				taskThread.setTaskService(taskService);
    				taskService.updateTaskStatus(task);
    				this.taskThreadPool.getTaskExecutor().execute(taskThread);
				}
			} catch (Exception e) {
				logger.error(String.format("Task_Taker Thread error![%s]",e));
			}
		}
	}

}
