package com.yihuacomputer.fish.api.person;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 用户操作日志服务
 *
 * @author shixiaolong
 * @version 0.1
 * @since 0.1
 * @date 2012-5-4
 */
public interface IUserLogService {
	/**
	 * 创建一个IUserLogService
	 *
	 * @return
	 */
	public IUserLog make();

	/**
	 * 增加系统操作日志
	 *
	 * @param log
	 * @return
	 */
	public IUserLog add(IUserLog log);

	/**
	 * 根据用户名删除系统操作日志
	 *
	 * @param code
	 */
	public void remove(String code);

	/**
	 * 分页列出系统操作日志
	 *
	 * @param offset
	 * @param limit
	 * @param slave
	 * @param filter
	 * @return
	 */
	public IPageResult<IUserLog> page(int offset, int limit);

	public IPageResult<IUserLog> page(int offset, int limit, IFilter filter);

	public IPageResult<IUserLog> page(int offset, int limit, IFilter filter, String orgId);

	/**
	 * 删除用户日志，（邯郸银行提的需求）
	 * @param currentTime
	 * @return
	 * @since 1.0.0
	 */
	public String deleteUserLogs(String currentTime);

	public IPageResult<IUserLog> searchLog(int offset, int limit, IFilter filter, String orgFlag);

}
