package com.yihuacomputer.fish.version.job;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author YiHua
 *
 */
public class JobThreadPool {
	private Logger logger = LoggerFactory.getLogger(JobThreadPool.class);

	private ThreadPoolExecutor jobThreadPoolExecutor;

	/**
	 * 初始化
	 */
	public JobThreadPool() {
		init();
	}

	private void init() {
		jobThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
				new PriorityBlockingQueue<Runnable>());
	}

	public ThreadPoolExecutor getJobThreadPoolExecutor() {
		return jobThreadPoolExecutor;
	}

	/**
	 * 关闭
	 */
	public void close(){
		if(jobThreadPoolExecutor!=null){
			jobThreadPoolExecutor.shutdownNow();
			logger.info("jobThreadPoolExecutor was closed!");
		}
	}

}
