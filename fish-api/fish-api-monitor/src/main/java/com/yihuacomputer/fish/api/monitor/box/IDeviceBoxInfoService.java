package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDeviceBoxInfoService {
	public IDeviceBoxInfo make();
	public IDeviceBoxInfo save(IDeviceBoxInfo dbi);
	public IDeviceBoxInfo update(IDeviceBoxInfo dbi);
	public IDeviceBoxInfo findByDeviceId(long deviceId);
	public void delete(IDeviceBoxInfo dbdi);
	public List<IDeviceBoxInfo> list(IFilter filter);
	public IPageResult<IDeviceBoxInfo> list(int offset,int limit,IFilter filter);

}
