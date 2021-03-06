package com.yihuacomputer.fish.api.openplan;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IOpenPlanService {

	/**
	 * @return
	 */
	public IOpenPlanDetail make();

	/**
	 * 保存开机方案
	 * @param openPlan
	 * @return
	 */
	public IDeviceOpenPlan saveOpenPlan(IDeviceOpenPlan openPlan);

	/**
	 * 更新方案
	 * @param openPlan
	 * @return
	 */
	public IDeviceOpenPlan updatePlan(IDeviceOpenPlan openPlan);

	/**
     * 删除方案
     * @param id
     */
    public void deletePlan(long id);

	/**
	 * 根据方案ID查找方案
	 * @param openPlanId
	 * @return
	 */
	public IDeviceOpenPlan getDeviceOpenPlanById(long openPlanId);

	/**
	 *
	 * @param name
	 * @return
	 */
	public IDeviceOpenPlan getDeviceOpenPlanByName(String name);
	/**
	 * 保存开机方案明细
	 * @param openPlanDetial
	 */
	public void saveOpenPlanDetail(IOpenPlanDetail openPlanDetial);


	/**
	 * 根据方案ID查找方案明细
	 * @param openPlanId
	 * @return
	 */
	public List<IOpenPlanDetail> getOpenPlanDetialById(long openPlanId);

	/**
	 *  根据条件分页查询方案
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IDeviceOpenPlan> page(int offset, int limit, IFilter filter);

	/**
	 * 根据日期列出哪些设备要进行开机率运算
	 * @param filter
	 * @return
	 */
	public List<IDevicePlanRelation> page(IFilter filter);

	/**
	 * @param id
	 * @return
	 */
	public int deviceCount(Long id);

	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceOpenPlan> list(IFilter filter);

	/**
	 * @param id
	 * @return
	 */
	public List<Object> deviceInfo(Long id);


}
