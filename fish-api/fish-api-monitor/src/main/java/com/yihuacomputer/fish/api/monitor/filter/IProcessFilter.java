package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

/**
 * 白名单进程监控条件
 * @author YiHua
 *
 */
public interface IProcessFilter {
	
	public List<Long> getSubOrg();

	public void setSubOrg(List<Long> subOrg);
	
	public boolean filterProcess(String orgId);
	
}
