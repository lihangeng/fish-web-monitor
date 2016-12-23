package com.yihuacomputer.fish.api.monitor.box;


import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface ICashInitRuleService {
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<ICashInitRule> page(int start,int limit,IFilter filter);
	/**
	 * @param deviceBoxInitRule
	 * @return
	 */
	public ICashInitRule update (ICashInitRule deviceBoxInitRule);
	/**
	 * @param id
	 * @return
	 */
	public ICashInitRule get (long id);
	/**
	 * @param ruleType
	 * @return
	 */
	public ICashInitRule get(BoxInitRuleType ruleType);
}
