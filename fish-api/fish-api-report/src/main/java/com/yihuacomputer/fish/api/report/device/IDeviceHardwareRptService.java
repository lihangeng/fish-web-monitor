package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IDeviceHardwareRptService {
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceHardwareRpt> listDeviceHardwareInfo(IFilter filter);
}
