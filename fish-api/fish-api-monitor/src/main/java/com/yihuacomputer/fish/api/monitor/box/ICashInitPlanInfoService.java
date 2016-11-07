package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface ICashInitPlanInfoService {
	
	public ICashInitPlanInfo make();
	public ICashInitPlanInfo save(ICashInitPlanInfo cashInitPlanInfo);
	public ICashInitPlanInfo update(ICashInitPlanInfo cashInitPlanInfo);
	public void remove(ICashInitPlanInfo cashInitPlanInfo);
	public ICashInitPlanInfo get(long id);
	
	public IPageResult<ICashInitPlanInfo> page(int offset,int limit,IFilter filter);
	public List<ICashInitPlanInfo> list(IFilter filter);
}
