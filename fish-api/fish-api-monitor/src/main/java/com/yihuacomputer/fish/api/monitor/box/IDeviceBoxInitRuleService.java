package com.yihuacomputer.fish.api.monitor.box;


import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDeviceBoxInitRuleService {
	public IPageResult<IDeviceBoxInitRule> page(int start,int limit,IFilter filter);
	public IDeviceBoxInitRule update (IDeviceBoxInitRule deviceBoxInitRule);
	public IDeviceBoxInitRule get (long id);
	public IDeviceBoxInitRule get(BoxInitRuleType ruleType);
}
