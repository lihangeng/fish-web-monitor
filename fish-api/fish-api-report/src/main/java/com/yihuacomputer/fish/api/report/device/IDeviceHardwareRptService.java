package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceHardwareRptService {
	
	public List<IDeviceHardwareRpt> listDeviceHardwareInfo(IFilter filter);
}
