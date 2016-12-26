package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

/**
 * 白名单进程监控条件
 * @author YiHua
 *
 */
public interface IProcessFilter {
	
	/**
	 * @return
	 */
	public List<Long> getSubOrg();

	/**
	 * @param subOrg
	 */
	public void setSubOrg(List<Long> subOrg);
	
	/**
	 * @param orgId
	 * @return
	 */
	public boolean filterProcess(String orgId);
	
}
