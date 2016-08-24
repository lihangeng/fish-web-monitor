package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 设备状态离线检查
 * @author YiHua
 * @since 2.1.1.1
 */
public class DeviceOfflineCheckJob extends AbstractYihuaJob{

	private Logger logger = LoggerFactory.getLogger(DeviceOfflineCheckJob.class);

	private int start = 0;

	private int end = 100;
	
	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		logger.debug("check atm offline ....");
		ICollectService collectService = super.getApplicationContext().getBean(ICollectService.class);
		IXfsService xfsService = super.getApplicationContext().getBean(IXfsService.class);
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
