package com.yihuacomputer.fish.version.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskThreadPool {

	private Logger logger = LoggerFactory.getLogger(TaskThreadPool.class);

	private ThreadPoolExecutor taskExecutor;
	
	public TaskThreadPool(){
		this.init();
	}
	 
	/**
	 * 获取执行器
	 * */
	public synchronized ThreadPoolExecutor getTaskExecutor(){
		return this.taskExecutor;
	}
	/**
	 * 初始化任务执行线程池
	 * */
	
	public void init(){
		taskExecutor = new ThreadPoolExecutor(30,30,10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(30),new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	public void close(){
		if(taskExecutor!=null){
			taskExecutor.shutdownNow();
			logger.info("version task Thread pool closed!");
		}		
	}
	
	/**
	 * 执行任务
	 * */
	public void execute(Runnable task){
		taskExecutor.execute(task);
	}
}
