package com.yihuacomputer.fish.api.atmlog;


import java.util.List;
import java.util.Map;

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
	
	
	public List<IAtmLogInfo> list(long orgId);
	
	public List<IAtmLogInfo> listByFilter(long orgId, IFilter filter);
	
	public Map<String,IAtmLogInfo> getBackUpInfo(String backupStartDay , String backupEndDay);
}
