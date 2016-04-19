package com.yihuacomputer.fish.parameter.publishjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;

public class PublishTaskManager implements Runnable {

	private Logger logger = LoggerFactory.getLogger(PublishTaskManager.class);


	private BlockingQueue<IParamPublishResult> queue ;

	private ThreadPoolExecutor taskExecutor;

	private IParamPublishResultService atmcParamPublishResultService;


	@Override
	public void run() {
		while (true) {
			try {
				IParamPublishResult paramPulishResult = this.queue.take();
				AtmcParamsJobThread jobThread = new AtmcParamsJobThread(paramPulishResult,atmcParamPublishResultService) ;
    			this.taskExecutor.execute(jobThread);
			} catch (Exception e) {
				logger.error(String.format("Task_Manager Thread error！[%s]",e));
			}
		}
	}

	public void init(BlockingQueue<IParamPublishResult> queue,ThreadPoolExecutor taskExecutor){
		this.queue = queue ;
		this.taskExecutor = taskExecutor ;
	}


	public void setAtmcParamPublishResultService(IParamPublishResultService atmcParamPublishResultService) {
		this.atmcParamPublishResultService = atmcParamPublishResultService;
	}

	public void addPublish(ParamPublishResult result ){
		try {
			this.queue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
