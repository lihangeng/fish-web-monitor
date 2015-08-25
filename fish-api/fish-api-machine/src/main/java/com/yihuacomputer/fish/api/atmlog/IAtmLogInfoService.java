package com.yihuacomputer.fish.api.atmlog;


import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmLogInfoService {	
	/**
	 * 分页查询日志统计情况
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmLogInfo> pageList(int start , int limit , IFilter filter,long orgId);
	
	
	public List<IAtmLogInfo> list(long orgId);
	
	public List<IAtmLogInfo> listByFilter(long orgId, IFilter filter);
}
