package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IDeviceUseCountRptService {
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceUseCountRpt> listDeviceUseCount(IFilter filter);
}
