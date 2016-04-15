package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IParamDeviceDetailService {
	IParamDeviceDetail get(long id);

	void update(IParamDeviceDetail paramDeviceDetail);

	List<DeviceParam> list(IFilter filter, long tabId, long deviceId);

	Iterable<IParamDeviceDetail> list();

	List<IParamDeviceDetail> list(IFilter filter);
	/**
	 * 获取同设备id下的最大版本号的数据
	 * 
	 * @param id
	 * @param timestamp 
	 * @return
	 */
	List<IParamDeviceDetail> getVersionTimeStampData(long id, long timestamp);
	/**
	 * 根据模板和设备详情获取参数
	 * @param filter
	 * @param tabId
	 * @param deviceId
	 * @return
	 */
	IParamDeviceDetail make();
	
	IParamDeviceDetail add(IParamDeviceDetail paramDeviceDetail);
	
	Iterable<IParamDeviceDetail> loadAll();
	
	IParamDeviceDetail getById(long elementId,long deviceId);
}
