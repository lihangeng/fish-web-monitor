package com.yihuacomputer.fish.parameter.publishjob;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.system.config.IParamService;

public class PublishJobManager {
	private Logger logger = LoggerFactory.getLogger(PublishJobManager.class);

	private PublishTaskManager taskManager ;

	@Autowired
	private IParamPublishResultService atmcParamPublishResultService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IParamService paramService;

	private BlockingQueue<IParamPublishResult> queue ;

	private String paramKey = "param_update_count";

	//通知设备的升级
	private int taskQueueLength = 30;

	private ThreadPoolExecutor taskExecutor;

	@PostConstruct
	public void init(){
		taskManager = new PublishTaskManager() ;
		taskManager.init(getQueue(), getThreadPool());
		taskManager.setParamPublishResultService(atmcParamPublishResultService);
		taskManager.setDeviceService(deviceService);
		Thread taskManagerThread = new Thread(taskManager);
		taskManagerThread.setName("Publish_Task_Manager");
		taskManagerThread.start();
	}

	public BlockingQueue<IParamPublishResult>  getQueue(){
		try{
			taskQueueLength = Integer.valueOf(paramService.getParam(paramKey).getParamValue());
			if(taskQueueLength>50){
				taskQueueLength=50;
			}
		}catch(Exception ex){
			logger.error(String.format("get param_update_count error [%s]",ex.getMessage()));
		}
		if(queue == null){
			queue = new ArrayBlockingQueue<IParamPublishResult>(this.taskQueueLength);
		}
		logger.info("taskQueueLength is " + this.taskQueueLength);
		return this.queue;
	}

	public ThreadPoolExecutor getThreadPool(){
		taskExecutor = new ThreadPoolExecutor(this.taskQueueLength,this.taskQueueLength,10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(this.taskQueueLength),new ThreadPoolExecutor.CallerRunsPolicy());
		return taskExecutor ;
	}

	public void addTask(IParamPublishResult result){
		try {
			this.queue.put(result) ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
