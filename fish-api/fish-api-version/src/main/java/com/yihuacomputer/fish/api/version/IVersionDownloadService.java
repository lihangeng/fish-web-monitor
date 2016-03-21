/**
 *
 */
package com.yihuacomputer.fish.api.version;


import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.job.IJob;

/**
 * 版本分发服务
 * @author xuxigang
 *
 */
public interface IVersionDownloadService {
	public IPageResult<?> pageDownDevices(int start,int limit,IVersion version,IFilter filter);
	/**
	 * 分页显示作业已经关联的设备列表
	 * @param start
	 * @param limit
	 * @param job
	 * @param filter
	 * @return
	 */
	public IPageResult<IDevice> pageLinkedDevices(int start,int limit,IJob job,IFilter filter);

	/**
	 * 重启ATM
	 * @param ips 设备的IP
	 */
	public void rebootATM(String ... ips);

	/**
	 * 全选设备时查询当前可下发的全部设备列表
	 * @param version
	 * @param org
	 * @return
	 */
	public IPageResult<Object> getCanPushDevicePagesInfo(int start, int limit,IVersion version,IFilter outerFilter);
	public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit,IVersion version,IFilter outerFilter);

	public boolean selectAllDeviceToTask(IJob job,IFilter outerFilter);
	


   	
}
