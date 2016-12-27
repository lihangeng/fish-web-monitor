package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IDeviceBoxDetailRptService {
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceBoxDetailRpt> listDeviceBoxDetail(IFilter filter);
}
