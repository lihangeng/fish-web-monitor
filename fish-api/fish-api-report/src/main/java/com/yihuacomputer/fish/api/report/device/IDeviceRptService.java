package com.yihuacomputer.fish.api.report.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * 设备基本信息统计报表服务接口 
 * @author YiHua
 *
 */
public interface IDeviceRptService {

	/**
	 * 根据机构列出设备明细
	 * @param orgId
	 * @return
	 */
	public List<IDeviceRpt> getDeviceByOrg(String orgId);

	/**
	 * 根据设备品牌列出设备明细
	 * @param vendorId
	 * @return
	 */
	public List<IDeviceRpt> getDeviceByVendor(String vendorId);
	
	/**
	 * 按各级机构统计不同品牌机器的保有情况
	 * @param orgId
	 * @return
	 */
	public List<IDeviceTypeCountRpt> getDeviceCountByOrg(String orgId);
	
	/**
	 * 根据条件列出设备明细
	 * @param filter
	 * @return
	 */
	public List<IDeviceRpt> listDevice(IFilter filter);
	
	
}
