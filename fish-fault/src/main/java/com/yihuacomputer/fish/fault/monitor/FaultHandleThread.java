package com.yihuacomputer.fish.fault.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.fault.monitor.IMessagHandleCollection;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

public class FaultHandleThread extends Thread{

	private Logger logger = LoggerFactory.getLogger(FaultHandleThread.class);

	private IDeviceCaseService deviceCaseService;

	private IMessagHandleCollection msgHandleCollection;

	private FaultThreadPool faultThreadPool;

	public FaultHandleThread(IDeviceCaseService deviceCaseService,IMessagHandleCollection msgHandleCollection,FaultThreadPool faultThreadPool){
		this.deviceCaseService = deviceCaseService;
		this.msgHandleCollection = msgHandleCollection;
		this.faultThreadPool = faultThreadPool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				IXfsStatus xfsStatus = this.msgHandleCollection.get();
				FaultHandle thread = new FaultHandle() ;
				thread.setTaskService(deviceCaseService);
				thread.setXfsStatus(xfsStatus);
    			this.faultThreadPool.getFaultExecutor().execute(thread);
			} catch (Exception e) {
				logger.error(String.format("FaultHandleThread error![%s]",e));
			}
		}
	}

}
