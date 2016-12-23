package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAuthorizationLogService {
	/**
	 * @return
	 */
	public IAuthorizationLog make();
	
	/**
	 * @param log
	 * @return
	 */
	public IAuthorizationLog add(IAuthorizationLog log);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAuthorizationLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IAuthorizationLog> export(IFilter filter);
}
