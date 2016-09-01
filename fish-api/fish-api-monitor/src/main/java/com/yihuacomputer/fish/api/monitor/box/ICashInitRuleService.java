package com.yihuacomputer.fish.api.monitor.box;


import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface ICashInitRuleService {
	public IPageResult<ICashInitRule> page(int start,int limit,IFilter filter);
	public ICashInitRule update (ICashInitRule deviceBoxInitRule);
	public ICashInitRule get (long id);
	public ICashInitRule get(BoxInitRuleType ruleType);
}
