package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamPublishSearchService {
	IPageResult<IParamPublish> page(int start,int limit,IFilter filter);
	Iterable<IParamPublish> list();
}
