package com.yihuacomputer.fish.machine.service.base;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.device.DeviceBrand;
import com.yihuacomputer.fish.api.device.IDeviceMeta;
import com.yihuacomputer.fish.machine.entity.device.DeviceMeta;
import com.yihuacomputer.fish.machine.service.api.IDomainDeviceMetaService;

public abstract class DomainDeviceMetaService implements IDomainDeviceMetaService {

	@Override
	public DeviceMeta make(DeviceBrand brand, String type) {
		return new DeviceMeta(brand, type);
	}

	@Override
	public IPageResult<IDeviceMeta> page(int offset, int limit, IFilter filter) {
		List<IDeviceMeta> lists = new ArrayList<IDeviceMeta>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IDeviceMeta>(lists,offset,limit);
	}

}
