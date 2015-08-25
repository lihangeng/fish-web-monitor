package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IMenuLogService {
	public IMenuLog make();
	
	public IMenuLog add(IMenuLog log);
	
	public IPageResult<IMenuLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IMenuLog> export(IFilter filter);
}
