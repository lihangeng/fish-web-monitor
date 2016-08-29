package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceBoxDetailRptService {
	
	public List<IDeviceBoxDetailRpt> listDeviceBoxDetail(IFilter filter);
}
