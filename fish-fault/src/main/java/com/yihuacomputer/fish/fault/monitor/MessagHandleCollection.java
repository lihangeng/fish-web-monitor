package com.yihuacomputer.fish.fault.monitor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.fault.monitor.IMessagHandleCollection;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.system.config.IParamService;

public class MessagHandleCollection implements IMessagHandleCollection{

	private Logger logger = LoggerFactory.getLogger(MessagHandleCollection.class);

	@Autowired
	private IParamService paramService;

	private String paramKey = "status_handle_count";

	//通知设备的升级
	private int faultHandleQueueLength = 30;

	private BlockingQueue<IXfsStatus> faultHandleQueue = null;

	@PostConstruct
	public void init(){
		try{
			faultHandleQueueLength = Integer.valueOf(paramService.getParam(paramKey).getParamValue());
		}catch(Exception ex){
			logger.error(String.format("get status_handle_count error [%s]",ex.getMessage()));
		}
	}

	/**
	 * 获取任务执行队列的使用权
	 * */
	public BlockingQueue<IXfsStatus> getfaultHandleQueue(){
		if(faultHandleQueue == null){
			faultHandleQueue = new ArrayBlockingQueue<IXfsStatus>(this.faultHandleQueueLength);
		}
		logger.info("faultHandleQueueLength is " + this.faultHandleQueueLength);
		return this.faultHandleQueue;
	}

	public int getFaultHandleQueueLength(){
		return this.faultHandleQueueLength;
	}

	@Override
	public void put(IXfsStatus xfsStatus) {
		try {
			this.getfaultHandleQueue().put(xfsStatus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IXfsStatus get() {
		try {
			return this.getfaultHandleQueue().take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
