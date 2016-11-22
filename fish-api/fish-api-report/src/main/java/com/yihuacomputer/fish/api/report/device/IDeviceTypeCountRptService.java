package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceTypeCountRptService {

	public List<IDeviceTypeCountRpt> listDeviceTypeCount(IFilter filter);
	/**
	 * ireport实现报表
	 * @param filter
	 * @return
	 */
	public List<IDeviceTypeCountJRpt> listDeviceTypeCountJReport(IFilter filter);
	
	
}
