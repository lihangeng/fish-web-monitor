package com.yihuacomputer.fish.parameter.publishjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPulishResult;
import com.yihuacomputer.fish.parameter.entity.ParamPulishResult;

public class PublishTaskManager implements Runnable {

	private Logger logger = LoggerFactory.getLogger(PublishTaskManager.class);

	private IParamPublishResultService atmcParamPublishResultService;

	private IDeviceService deviceService;

	private BlockingQueue<IParamPulishResult> queue ;

	private ThreadPoolExecutor taskExecutor;

	@Override
	public void run() {
		while (true) {
			try {
				IParamPulishResult paramPulishResult = this.queue.take();
				AtmcParamsJobThread jobThread = new AtmcParamsJobThread(paramPulishResult) ;
				jobThread.setParamPublishResultService(atmcParamPublishResultService);
				jobThread.setDeviceService(deviceService);
    			this.taskExecutor.execute(jobThread);
			} catch (Exception e) {
				logger.error(String.format("Task_Manager Thread error！[%s]",e));
			}
		}
	}

	public void init(BlockingQueue<IParamPulishResult> queue,ThreadPoolExecutor taskExecutor){
		this.queue = queue ;
		this.taskExecutor = taskExecutor ;
	}

//	public IAtmcParamPublishResultService getAtmcParamPublishResultService() {
//		return atmcParamPublishResultService;
//	}

//	public void setAtmcParamPublishResultService(
//			IAtmcParamPublishResultService atmcParamPublishResultService) {
//		this.atmcParamPublishResultService = atmcParamPublishResultService;
//	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void addPublish(ParamPulishResult result ){
		try {
			this.queue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
