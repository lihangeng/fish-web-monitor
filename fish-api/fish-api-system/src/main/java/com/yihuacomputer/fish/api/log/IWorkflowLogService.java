package com.yihuacomputer.fish.api.log;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IWorkflowLogService {
	public IWorkflowLog make();
	
	public IWorkflowLog add(IWorkflowLog log);
	
	public IPageResult<IWorkflowLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出日志
	 * @param filter
	 * @return
	 */
	public List<IWorkflowLog> export(IFilter filter);
}
