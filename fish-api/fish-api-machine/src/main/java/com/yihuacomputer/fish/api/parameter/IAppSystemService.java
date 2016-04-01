package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAppSystemService {
	IAppSystem get(long id);
	void update(IAppSystem appSystem);
	IPageResult<IAppSystem> page(int start,int limit,IFilter filter);
	Iterable<IAppSystem> list();
}
