package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceTypeCountRptService {
	
	public List<IDeviceTypeCountRpt> listDeviceTypeCount(IFilter filter);
}
