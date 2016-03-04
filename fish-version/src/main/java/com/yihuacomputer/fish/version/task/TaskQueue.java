package com.yihuacomputer.fish.version.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.version.job.task.ITask;

public class TaskQueue {

	private Logger logger = LoggerFactory.getLogger(TaskQueue.class);
	
	@Autowired
	private IParamService paramService;
	
	private String paramKey = "device_update_count";
	
	//通知设备的升级
	private int taskQueueLength = 30;
	
	private BlockingQueue<ITask> taskQueue = null;

	@PostConstruct
	public void init(){
		try{
			taskQueueLength = Integer.valueOf(paramService.getParam(paramKey).getParamValue());
			if(taskQueueLength>30){
				taskQueueLength=30;
			}
		}catch(Exception ex){
			logger.error(String.format("get device_update_count error [%s]",ex.getMessage()));
		}
	}
	
	/**
	 * 获取任务执行队列的使用权
	 * */
	public BlockingQueue<ITask> getTaskQueue(){
		if(taskQueue == null){
			taskQueue = new ArrayBlockingQueue<ITask>(this.taskQueueLength);
		}
		logger.info("taskQueueLength is " + this.taskQueueLength);
		return this.taskQueue;
	}
	
	public int getTaskQueueLength(){	
		return this.taskQueueLength;
	}

}
