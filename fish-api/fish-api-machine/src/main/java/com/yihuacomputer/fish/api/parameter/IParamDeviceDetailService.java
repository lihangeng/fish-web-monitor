package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IParamDeviceDetailService {
	IParamDeviceDetail get(long id);

	void update(IParamDeviceDetail paramDeviceDetail);

	List<DeviceParam> list(IFilter filter, long tabId, long deviceId);

	Iterable<IParamDeviceDetail> list();

	/**
	 * 获取同设备id下的最大版本号的数据
	 * 
	 * @param id
	 * @param timestamp 
	 * @return
	 */
	List<IParamDeviceDetail> getVersionTimeStampData(long id, long timestamp);
}
