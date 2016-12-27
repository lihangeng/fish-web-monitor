/**
 *
 */
package com.yihuacomputer.fish.fault.monitor;

import org.slf4j.Logger;

import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 * @author xuxigang
 *
 */
public class FaultHandle implements Runnable {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(FaultHandle.class);

	private IXfsStatus xfsStatus;
	private IDeviceCaseService deviceCaseService;

	public FaultHandle() {
	}

	/**
	 * @param xfsStatus
	 */
	public FaultHandle(IXfsStatus xfsStatus) {
		this.xfsStatus = xfsStatus;
	}

	public void setTaskService(IDeviceCaseService deviceCaseService) {
		this.deviceCaseService = deviceCaseService;
	}

	public IXfsStatus getXfsStatus() {
		return xfsStatus;
	}

	public void setXfsStatus(IXfsStatus xfsStatus) {
		this.xfsStatus = xfsStatus;
	}

	@Override
	public void run() {
		logger.info(String.format("handle status start,terminal id is [%s]",xfsStatus.getTerminalId()));
		deviceCaseService.handleModStatus(xfsStatus);
	}

}
