package com.yihuacomputer.fish.web.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 将当天报停设备信息更新到设备信息表中
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
public class QuittingDevInfoUpdateJob extends AbstractYihuaJob {

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		IDeviceService deviceService = super.getApplicationContext().getBean(IDeviceService.class);
	    IQuittingNoticeService quittingNoticeService = super.getApplicationContext().getBean(IQuittingNoticeService.class);
	    IXfsService xfsService = super.getApplicationContext().getBean(IXfsService.class);
	    ICollectService collectService = super.getApplicationContext().getBean(ICollectService.class);
	    IRunInfoService runInfoService = super.getApplicationContext().getBean(IRunInfoService.class);
		//1.处理停机时间是当天的
		String strDayTime = DateUtils.getDate(new Date())+" 00:00:00";
		Date dayTime = DateUtils.getTimestamp(strDayTime);
		IFilter filter = new Filter();
		filter.eq("stopTime", dayTime);
		Iterable<IQuittingNotice> quittingNoticeList = quittingNoticeService.list(filter);
		for(IQuittingNotice quittingNotice : quittingNoticeList)
		{
			quittingNotice.setDevStatus(DevStatus.DISABLED);
			quittingNoticeService.update(quittingNotice);
			IDevice device = deviceService.get(quittingNotice.getDeviceCode());
			if (quittingNotice.getDevStatus() != null) {
				// 当设备报停时,更新设备表状态
				if (!device.getStatus().equals(quittingNotice.getDevStatus())) {
					device.setStatus(quittingNotice.getDevStatus());
					deviceService.update(device);
				}
				/* 停机开始 */
				if (quittingNotice.getDevStatus().equals(DevStatus.DISABLED)) {
					IRunInfo runInfo = runInfoService.make();
					runInfo.setTerminalId(quittingNotice.getDeviceCode());
					runInfo.setRunStatus(RunStatus.StopManmade);
					collectService.collectATMCRunInfo(
							quittingNotice.getDeviceCode(), runInfo);
				}
			}
		}
		//2.处理开启时间是当天的
		filter = new Filter();
		filter.eq("openTime", dayTime);
		quittingNoticeList = quittingNoticeService.list(filter);
		for(IQuittingNotice nextQuittingNotice : quittingNoticeList)
		{
			nextQuittingNotice.setDevStatus(DevStatus.OPEN);
			quittingNoticeService.update(nextQuittingNotice);

			IDevice device = deviceService.get(nextQuittingNotice.getDeviceCode());
			device.setStatus(DevStatus.OPEN);
			deviceService.update(device);		

			IXfsStatus status = xfsService.loadXfsStatus(nextQuittingNotice.getDeviceCode());
			status.setRunStatus(RunStatus.Unknown);
			xfsService.updateXfsStatus(status);
		}
		
	}

}
