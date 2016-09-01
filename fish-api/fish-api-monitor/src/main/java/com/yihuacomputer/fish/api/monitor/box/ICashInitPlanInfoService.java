package com.yihuacomputer.fish.api.monitor.box;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface ICashInitPlanInfoService {
	/**
	 * 生成当天的加钞计划
	 */
	public void generalCashInitPlan();
	/**
	 * 根据日期和机构生成加钞计划
	 * @param organization
	 * @param cashInitDate yyyyMMdd
	 */
	public void generalCashInitPlan(IOrganization organization,String cashInitDate);
	public ICashInitPlanInfo make();
	public ICashInitPlanInfo save(ICashInitPlanInfo cashInitPlanInfo);
	public ICashInitPlanInfo update(ICashInitPlanInfo cashInitPlanInfo);
	public void remove(ICashInitPlanInfo cashInitPlanInfo);
	public ICashInitPlanInfo get(long id);
	
	public IPageResult<ICashInitPlanInfo> page(int offset,int limit,IFilter filter);
}
