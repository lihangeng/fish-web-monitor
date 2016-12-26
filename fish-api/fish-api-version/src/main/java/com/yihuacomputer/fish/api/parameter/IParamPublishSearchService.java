package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IParamPublishSearchService {
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	IPageResult<IParamPublish> page(int start,int limit,IFilter filter);
	/**
	 * @return
	 */
	Iterable<IParamPublish> list();
}
