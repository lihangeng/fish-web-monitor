package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

public interface IDeviceBoxDetailInfoService {
	public IDeviceBoxDetailInfo make();

	public IDeviceBoxDetailInfo save(IDeviceBoxDetailInfo dbdi);

	public IDeviceBoxDetailInfo update(IDeviceBoxDetailInfo dbdi);

	public void delete(IDeviceBoxDetailInfo dbdi);

	public List<IDeviceBoxDetailInfo> list(IFilter filter);
	public Map<String,IDeviceBoxDetailInfo> getCashIdMap(IFilter filter);
	
	public boolean updateBoxDetailEffect(long deviceBoxInfoId);
}
