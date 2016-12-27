package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;

/**
 * 
 * @author xuxiang
 *
 */
@Service("devInfoUpdateJob")
@Transactional
public class DeviceInfoUpdateTask {
	private Logger logger = LoggerFactory.getLogger(DeviceInfoUpdateTask.class);

	@Autowired
	private IDeviceService deviceService;

	/**
	 * 设备信息更改
	 */
	public void deviceInfoUpdate() {
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
