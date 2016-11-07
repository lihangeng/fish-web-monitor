package com.yihuacomputer.fish.api.monitor.box;

import com.yihuacomputer.fish.api.person.IOrganization;

public interface ICashInitPlanInfoExtractDataService {

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
}
