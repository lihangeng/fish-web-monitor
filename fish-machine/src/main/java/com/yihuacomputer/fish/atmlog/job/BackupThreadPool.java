package com.yihuacomputer.fish.atmlog.job;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 备份线程池
 * @author YiHua
 *
 */
public class BackupThreadPool{
	
	private Logger logger = LoggerFactory.getLogger(BackupThreadPool.class);

	private ThreadPoolExecutor backupExecutor;
	
	private int corePoolSize = 30;
	
	private BlockingQueue<Runnable> backupExcuters = new ArrayBlockingQueue<Runnable>(BackupQueue.getBackupQueueLength());
	
	public int getCorePoolSize(){
		return this.corePoolSize;
	}
	/**
	 * 初始化
	 */
	public BackupThreadPool(){
		init();
	}
	/**
	 * 获取执行器
	 * */
	public synchronized ThreadPoolExecutor getTaskExecutor(){
		return this.backupExecutor;
	}
	/**
	 * 初始化任务执行线程池
	 * */
	public void init(){
		backupExecutor = new ThreadPoolExecutor(corePoolSize,60,10,TimeUnit.SECONDS,this.backupExcuters,new ThreadPoolExecutor.CallerRunsPolicy()); 
	}

	/**
	 * 关闭
	 */
	public void close(){
		backupExecutor.shutdownNow();
		logger.info("File backup thread pool closed!");		
	}
	/**
	 * 执行任务
	 * @param task 
	 * BackupThreadPool.java
	 * */
	public void execute(Runnable task){
		backupExecutor.execute(task);
	}
	
	public int getActvieCount(){
		if(backupExecutor!=null){
			return backupExecutor.getActiveCount();
		}else{
			return -1;
		}
	}
}
