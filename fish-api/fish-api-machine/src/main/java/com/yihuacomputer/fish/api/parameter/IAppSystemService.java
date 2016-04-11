package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAppSystemService {
	IAppSystem get(long id);

	void update(IAppSystem appSystem);

	IPageResult<IAppSystem> page(int start, int limit, IFilter filter);

	Iterable<IAppSystem> list();

	/**
	 * 得到参数应用分类
	 * 
	 * @return
	 */
	List<IAppSystem> getBelongs();
}
