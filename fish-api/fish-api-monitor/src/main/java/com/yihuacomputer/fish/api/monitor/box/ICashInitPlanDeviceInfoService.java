package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface ICashInitPlanDeviceInfoService {
	public ICashInitPlanDeviceInfo make();
	public ICashInitPlanDeviceInfo save(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public ICashInitPlanDeviceInfo update(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public void remove(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public ICashInitPlanDeviceInfo get(long id);
	public List<ICashInitPlanDeviceInfo> list(IFilter filter);
//	public List<Object> listPage(IFilter filter);
	public List<CashInitPlanDeviceInfoForm> listSelectAble(ICashInitPlanInfo planInfo,IFilter filter);
}
