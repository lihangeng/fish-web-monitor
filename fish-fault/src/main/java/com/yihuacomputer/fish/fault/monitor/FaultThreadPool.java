package com.yihuacomputer.fish.fault.monitor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YiHua
 *
 */
public class FaultThreadPool {

	private Logger logger = LoggerFactory.getLogger(FaultThreadPool.class);

	private ThreadPoolExecutor faultExecutor;

	/**
	 * 初始化任务执行线程池
	 */
	public FaultThreadPool(){
		this.init();
	}

	/**
	 * 获取执行器
	 * */
	public synchronized ThreadPoolExecutor getFaultExecutor(){
		return this.faultExecutor;
	}
	/**
	 * 初始化任务执行线程池
	 * */

	public void init(){
		faultExecutor = new ThreadPoolExecutor(30,30,10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(30),new ThreadPoolExecutor.CallerRunsPolicy());
	}

	/**
	 * 关闭
	 */
	public void close(){
		if(faultExecutor!=null){
			faultExecutor.shutdownNow();
			logger.info("fault handle Thread pool closed!");
		}
	}

	/**
	 * 执行任务
	 * @param faultHandle
	 * */
	public void execute(Runnable faultHandle){
		faultExecutor.execute(faultHandle);
	}
}
