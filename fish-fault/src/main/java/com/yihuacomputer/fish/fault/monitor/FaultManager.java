package com.yihuacomputer.fish.fault.monitor;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.fault.monitor.IFaultManager;
import com.yihuacomputer.fish.api.fault.monitor.IMessagHandleCollection;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 * @author YiHua
 *
 */
public class FaultManager implements IFaultManager {

	private Logger logger = LoggerFactory.getLogger(FaultManager.class);

	@Autowired(required = false)
    private IDeviceCaseService deviceCaseService;

	@Autowired
	private IMessagHandleCollection msgHandleCollection;

	private FaultHandleThread faultHandleThread;

	private FaultThreadPool faultThreadPool;

	@Override
	public void handleFault(IXfsStatus xfsStatus) {
		msgHandleCollection.put(xfsStatus);
	}

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		this.faultThreadPool = new FaultThreadPool();
		this.initFaultHandle();
	}

	private void initFaultHandle() {
		faultHandleThread = new FaultHandleThread(deviceCaseService,msgHandleCollection,faultThreadPool);
		faultHandleThread.setName("fault_handle");
		faultHandleThread.start();
		logger.info(String.format("started thread faultHandleThread = %s" ,faultHandleThread.getName()));
	}


}
