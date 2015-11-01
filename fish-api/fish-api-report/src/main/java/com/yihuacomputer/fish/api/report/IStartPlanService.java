package com.yihuacomputer.fish.api.report;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IStartPlanService {
	public IStartPlan make();

	public IStartPlan get(long id);
	
	public IStartPlan get(String name);

	public IStartPlan add(IStartPlan plan);

	public void remove(long id);

	public void update(IStartPlan plan);

	public Iterable<IStartPlan> list();

	public IPageResult<IStartPlan> page(int offset, int limit, IFilter filter);

	public Iterable<IStartPlan> list(IFilter filter);
}
