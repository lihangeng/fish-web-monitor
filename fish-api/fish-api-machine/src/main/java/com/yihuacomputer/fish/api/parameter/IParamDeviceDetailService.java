package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IParamDeviceDetailService {
	IParamDeviceDetail get(long id);
	void update(IParamDeviceDetail paramDeviceDetail);
	List<DeviceParam> list(IFilter filter,long tabId,long deviceId);
	Iterable<IParamDeviceDetail> list();
}
