package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;

/**
 * 设备状态离线检查
 * @author YiHua
 */
@Service("offlineJob")
public class DeviceOfflineCheck{

	private Logger logger = LoggerFactory.getLogger(DeviceOfflineCheck.class);

	private final String jobId = "offlinetask";
	
	@Autowired
	private ICollectService collectService;

	@Autowired
	private IXfsService xfsService;

    @Autowired
    private IJobSynchService jobSynchService;
    
	private int start = 0;

	private int end = 100;

	public void deviceOffline() {
		
		logger.debug("check atm offline ....");
    	if(!jobSynchService.getJobRunPriviledge(jobId)){
    		logger.debug("check atm offline job is running");
    		return;
    	}
    	
		try {
			List<?> loadOfflineStatus = xfsService.loadOfflineStatus(this.start, this.end, DateUtils.getPreMinuteTimestamp(MonitorCfg.getDeviceOffline()));
			
			for (Object offlineObj : loadOfflineStatus) {
				String terminalId = String.valueOf(offlineObj);
				IXfsStatus offline = xfsService.makeOfflineXfsStatus();

				offline.setTerminalId(terminalId);
				collectService.collectModuleStatus(terminalId, offline);
			}

		} catch (Exception e) {
			logger.error(String.format("Device Status offline check thread error![%s]", e));
		}
		logger.debug("check atm offline finished");

	}
}
