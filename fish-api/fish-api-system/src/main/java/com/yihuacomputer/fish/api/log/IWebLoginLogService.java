package com.yihuacomputer.fish.api.log;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * web系统登录日志服务
 * @author xuxigang
 * @version
 * @since  0.2
 * @date 2010-11-15
 */
public interface IWebLoginLogService {
	public IWebLoginLog make();
	public IWebLoginLog add(IWebLoginLog log);
	/**
	 * 修改登出时间
	 * @param sessionId
	 * @param logoutTime
	 */
	public void updateLogoutTime(String sessionId,Date logoutTime);
	public IPageResult<IWebLoginLog> page(int offset,int limit,IFilter filter);
	/**
	 * 导出登录日志
	 * @param filter
	 * @return
	 */
	public List<IWebLoginLog> export(IFilter filter);
}
