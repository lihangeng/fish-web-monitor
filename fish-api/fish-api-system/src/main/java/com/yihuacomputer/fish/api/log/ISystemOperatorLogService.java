package com.yihuacomputer.fish.api.log;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 系统操作日志服务
 * @author xuxigang
 * @version 0.1
 * @since 0.1
 * @date 2010-8-3
 */
public interface ISystemOperatorLogService {
	/**
	 * 创建一个ISystemOperatorLog
	 * @return
	 */
	public ISystemOperatorLog make();
	/**
	 * 增加系统操作日志
	 * @param log
	 * @return
	 */
	public ISystemOperatorLog add(ISystemOperatorLog log);
	/**
	 * 分页列出系统操作日志
	 * @param offset
	 * @param limit
	 * @param slave
	 * @param filter
	 * @return
	 */
	public IPageResult<ISystemOperatorLog> page(int offset,int limit,IFilter filter);

}
