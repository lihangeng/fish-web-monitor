package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceHardwareRptService {
	
	public List<IDeviceHardwareRpt> listDeviceHardwareInfo(IFilter filter);
}
