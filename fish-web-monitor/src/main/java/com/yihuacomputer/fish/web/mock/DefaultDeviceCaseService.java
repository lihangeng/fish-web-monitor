package com.yihuacomputer.fish.web.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

@Service
public class DefaultDeviceCaseService  implements IDeviceCaseService{

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private DefaultHwFaultService hwFaultService;

	@Override
	public void handleDeviceRegister(IDeviceRegister deviceRegister) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleRetainCard(IRetaincard retainCard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleRumtimeInfo(IRuntimeInfo runtimeInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleModStatus(IXfsStatus xfsStatus,IXfsStatus hisXfsStatus) {
		/*处理故障*/
		hwFaultService.handleModFualt(xfsStatus,hisXfsStatus);
	}

	@Override
	public void handleAppStatus(IRunInfo appStatus) {
	}

	@Override
	public void handleModStatus(IXfsStatus xfsStatus) {
		/*处理故障*/
		hwFaultService.handleModFualt(xfsStatus,xfsStatus.getHisXfsStatus());

	}
}
