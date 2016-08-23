package com.yihuacomputer.fish.api.monitor.box;

public interface ICashInitPlanDeviceInfoService {
	public ICashInitPlanDeviceInfo make();
	public ICashInitPlanDeviceInfo save(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public ICashInitPlanDeviceInfo update(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public void remove(ICashInitPlanDeviceInfo cashInitPlanInfo);
	public ICashInitPlanDeviceInfo get(long id);
}
