package com.yihuacomputer.fish.version.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YiHua
 *
 */
public class TaskThreadPool {

	private Logger logger = LoggerFactory.getLogger(TaskThreadPool.class);

	private ThreadPoolExecutor taskExecutor;
	
	private TaskQueue taskQueue;
	
	/**
	 * @param taskQueue
	 */
	public TaskThreadPool(TaskQueue taskQueue){
		this.taskQueue = taskQueue;
		this.init();
	}
	
	public TaskQueue getTaskQueue() {
		return taskQueue;
	}
	
	public int getCorePoolSize(){
		return this.taskQueue.getTaskQueueLength();
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
		taskExecutor = new ThreadPoolExecutor(getCorePoolSize(),getCorePoolSize(),10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(this.taskQueue.getTaskQueueLength()),new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	/**
	 * 关闭
	 */
	public void close(){
		if(taskExecutor!=null){
			taskExecutor.shutdownNow();
			logger.info("version task Thread pool closed!");
		}		
	}
	
	/**
	 * 执行任务
	 * @param task
	 * */
	public void execute(Runnable task){
		taskExecutor.execute(task);
	}
}
