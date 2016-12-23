package com.yihuacomputer.fish.api.atmlog;


import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IAtmLogInfoService {	
	/**
	 * 分页查询日志统计情况
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	
	
	/**
	 * @param orgId
	 * @return
	 */
	public List<IAtmLogInfo> list(long orgId);
	
	/**
	 * @param orgId
	 * @param filter
	 * @return
	 */
	public List<IAtmLogInfo> listByFilter(long orgId, IFilter filter);
	
	/**
	 * @param backupStartDay
	 * @param backupEndDay
	 * @return
	 */
	public Map<String,IAtmLogInfo> getBackUpInfo(String backupStartDay , String backupEndDay);
}
