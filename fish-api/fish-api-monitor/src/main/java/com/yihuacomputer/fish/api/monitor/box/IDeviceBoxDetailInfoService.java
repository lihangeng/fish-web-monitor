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
	/**
	 * 获取以钞箱ID为key的钞箱明细信息集合
	 * @param filter
	 * @return
	 */
	public Map<String,IDeviceBoxDetailInfo> getCashIdMap(IFilter filter);
	
	/**
	 * 更新指定设备钞箱状态为失效状态
	 * @param deviceBoxInfoId
	 * @return
	 */
	public boolean updateBoxDetailEffect(long deviceBoxInfoId);
}
