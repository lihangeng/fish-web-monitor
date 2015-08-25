package com.yihuacomputer.fish.api.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDeviceMetaService {

	public IDeviceMeta make(DeviceBrand brand, String type);
	
	public IDeviceMeta get(long id);
	
	public IDeviceMeta get(DeviceBrand brand, String type);
	
	public IDeviceMeta add(IDeviceMeta meta);
	
	public IDeviceMeta add(DeviceBrand brand, String type);
	
	public void remove(long id);
	
	public void remove(DeviceBrand brand, String type);
	
	//public void update(IDeviceMeta meta);
	
	public List<IDeviceMeta> list();
	
	public IPageResult<IDeviceMeta> page(int offset, int limit,IFilter filter);
	
	public List<IDeviceMeta> list(IFilter filter);
}
