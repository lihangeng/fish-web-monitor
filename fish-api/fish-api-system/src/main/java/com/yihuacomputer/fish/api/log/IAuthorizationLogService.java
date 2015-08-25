package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAuthorizationLogService {
	public IAuthorizationLog make();
	
	public IAuthorizationLog add(IAuthorizationLog log);
	
	public IPageResult<IAuthorizationLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IAuthorizationLog> export(IFilter filter);
}
