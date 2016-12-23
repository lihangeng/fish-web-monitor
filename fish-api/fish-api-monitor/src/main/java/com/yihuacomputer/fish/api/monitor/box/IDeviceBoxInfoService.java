package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IDeviceBoxInfoService {
	/**
	 * @return
	 */
	public IDeviceBoxInfo make();
	/**
	 * @param id
	 * @return
	 */
	public IDeviceBoxInfo get(long id);
	/**
	 * @param dbi
	 * @return
	 */
	public IDeviceBoxInfo save(IDeviceBoxInfo dbi);
	/**
	 * @param dbi
	 * @return
	 */
	public IDeviceBoxInfo update(IDeviceBoxInfo dbi);
	/**
	 * @param deviceId
	 * @return
	 */
	public IDeviceBoxInfo findByDeviceId(long deviceId);
	/**
	 * @param dbdi
	 */
	public void delete(IDeviceBoxInfo dbdi);
	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceBoxInfo> list(IFilter filter);
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IDeviceBoxInfo> list(int offset,int limit,IFilter filter);
	/**
	 * 同步设备预警值(filter 中设备型号ID-deviceId.devType.id，用户所在的机构-deviceId.organization.orgFlag)
	 * @param deviceBoxInfo
	 * @param filter
	 * @return
	 */
	boolean synchronizedUpdate(IDeviceBoxInfo deviceBoxInfo,IFilter filter);
	/**
	 * 根据机构Flag查找需要加钞的设备
	 * @param orgFlag 不需要加%
	 * @return
	 */
	public List<IDeviceBoxInfo> getCashLimitRuleDevice(String orgFlag);
	/**
	 * 获取机构下所有的钞箱信息
	 * @param orgFlag
	 * @return
	 */
	public Map<String,IDeviceBoxInfo> getDeviceBoxInfo(String orgFlag);
}
