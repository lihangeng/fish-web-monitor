package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IWorkflowLogService {
	/**
	 * @return
	 */
	public IWorkflowLog make();
	
	/**
	 * @param log
	 * @return
	 */
	public IWorkflowLog add(IWorkflowLog log);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IWorkflowLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IWorkflowLog> export(IFilter filter);
}
