package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceUseCountRptService {
	
	public List<IDeviceUseCountRpt> listDeviceUseCount(IFilter filter);
}
