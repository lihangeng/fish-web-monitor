package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IDeviceBoxDetailInfoService {
	/**
	 * @return
	 */
	public IDeviceBoxDetailInfo make();

	/**
	 * @param dbdi
	 * @return
	 */
	public IDeviceBoxDetailInfo save(IDeviceBoxDetailInfo dbdi);

	/**
	 * @param dbdi
	 * @return
	 */
	public IDeviceBoxDetailInfo update(IDeviceBoxDetailInfo dbdi);

	/**
	 * @param dbdi
	 */
	public void delete(IDeviceBoxDetailInfo dbdi);

	/**
	 * @param filter
	 * @return
	 */
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
