package com.yihuacomputer.fish.parameter.publishjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;

public class PublishTaskManager implements Runnable {

	private Logger logger = LoggerFactory.getLogger(PublishTaskManager.class);

	private IParamPublishResultService paramPublishResultService;

	private IDeviceService deviceService;

	private BlockingQueue<IParamPublishResult> queue ;

	private ThreadPoolExecutor taskExecutor;

	@Override
	public void run() {
		while (true) {
			try {
				IParamPublishResult paramPulishResult = this.queue.take();
				AtmcParamsJobThread jobThread = new AtmcParamsJobThread(paramPulishResult) ;
				jobThread.setParamPublishResultService(paramPublishResultService);
				jobThread.setDeviceService(deviceService);
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

	public IParamPublishResultService getParamPublishResultService() {
		return paramPublishResultService;
	}

	public void setParamPublishResultService(
			IParamPublishResultService atmcParamPublishResultService) {
		this.paramPublishResultService = atmcParamPublishResultService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void addPublish(ParamPublishResult result ){
		try {
			this.queue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
