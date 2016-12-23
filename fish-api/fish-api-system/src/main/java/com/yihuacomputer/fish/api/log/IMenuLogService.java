package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IMenuLogService {
	/**
	 * @return
	 */
	public IMenuLog make();
	
	/**
	 * @param log
	 * @return
	 */
	public IMenuLog add(IMenuLog log);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IMenuLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IMenuLog> export(IFilter filter);
}
