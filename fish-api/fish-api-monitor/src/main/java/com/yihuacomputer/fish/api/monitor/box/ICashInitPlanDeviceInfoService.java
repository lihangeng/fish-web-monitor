package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface ICashInitPlanDeviceInfoService {
	/**
	 * @return
	 */
	public ICashInitPlanDeviceInfo make();
	/**
	 * @param cashInitPlanInfo
	 * @return
	 */
	public ICashInitPlanDeviceInfo save(ICashInitPlanDeviceInfo cashInitPlanInfo);
	/**
	 * @param cashInitPlanInfo
	 * @return
	 */
	public ICashInitPlanDeviceInfo update(ICashInitPlanDeviceInfo cashInitPlanInfo);
	/**
	 * @param cashInitPlanInfo
	 */
	public void remove(ICashInitPlanDeviceInfo cashInitPlanInfo);
	/**
	 * @param id
	 * @return
	 */
	public ICashInitPlanDeviceInfo get(long id);
	/**
	 * @param filter
	 * @return
	 */
	public List<ICashInitPlanDeviceInfo> list(IFilter filter);
	/**
	 * 列出当前加钞计划可以添加的设备列表信息
	 * @param cashInitPlanInfo(加钞计划)
	 * @param filter(其他过滤条件)
	 * @return 当前加钞计划可以添加的设备列表信息
	 */
	public List<CashInitPlanDeviceInfoForm> listSelectAble(ICashInitPlanInfo cashInitPlanInfo,IFilter filter);
}
