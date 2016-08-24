package com.yihuacomputer.fish.web.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 定时将当天未生效的设备信息更新到信息表中
 * @author xuxiang
 * @since 2.1.1.1
 */
public class DeviceInfoUpdateJob extends AbstractYihuaJob{
	
	private Logger logger = LoggerFactory.getLogger(DeviceInfoUpdateJob.class);

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		IDeviceService deviceService = super.getApplicationContext().getBean(IDeviceService.class);
		try {
			StringBuilder hql = new StringBuilder();
			List<Object> valueObj = new ArrayList<Object>();
			hql.append("select t from Device t,QuittingNotice q where q.deviceCode = t.terminalId and t.status=? and  q.openTime = ?");
			valueObj.add(DevStatus.DISABLED);
			String date = DateUtils.getDate(new Date());
			valueObj.add(DateUtils.getDate(date));
			List<IDevice> devices = deviceService.list(hql.toString(), valueObj);
			for (IDevice device : devices) {
				device.setStatus(DevStatus.OPEN);
				deviceService.update(device);
			}
		} catch (Exception e) {
			logger.error(String.format("DeviceInfo update thread error![%s]", e));
		}
	}

}
