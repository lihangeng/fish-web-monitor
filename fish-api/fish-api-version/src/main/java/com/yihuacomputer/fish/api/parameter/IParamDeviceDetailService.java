package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IParamDeviceDetailService {
	/**
	 * @param id
	 * @return
	 */
	IParamDeviceDetail get(long id);

	/**
	 * @param paramDeviceDetail
	 */
	void update(IParamDeviceDetail paramDeviceDetail);

	/**
	 * @param filter
	 * @param tabId
	 * @param deviceId
	 * @return
	 */
	List<DeviceParam> list(IFilter filter, long tabId, long deviceId);

	/**
	 * @return
	 */
	Iterable<IParamDeviceDetail> list();

	/**
	 * @param filter
	 * @return
	 */
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
	 * @return
	 */
	IParamDeviceDetail make();
	
	/**
	 * @param paramDeviceDetail
	 * @return
	 */
	IParamDeviceDetail add(IParamDeviceDetail paramDeviceDetail);
	
	/**
	 * @return
	 */
	Iterable<IParamDeviceDetail> loadAll();
	
	/**
	 * @param elementId
	 * @param deviceId
	 * @return
	 */
	IParamDeviceDetail getById(long elementId,long deviceId);
}
