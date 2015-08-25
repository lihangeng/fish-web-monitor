package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceUseCountRptService {
	
	public List<IDeviceUseCountRpt> listDeviceUseCount(IFilter filter);
}
