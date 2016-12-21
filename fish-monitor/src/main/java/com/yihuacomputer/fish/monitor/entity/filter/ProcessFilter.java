package com.yihuacomputer.fish.monitor.entity.filter;

import java.util.List;

import com.yihuacomputer.fish.api.monitor.filter.IProcessFilter;

public class ProcessFilter implements IProcessFilter {

	private List<Long> subOrg;

	@Override
	public List<Long> getSubOrg() {
		return subOrg;
	}

	@Override
	public void setSubOrg(List<Long> subOrg) {
		this.subOrg = subOrg;
	}

	@Override
	public boolean filterProcess(String orgId) {
		return this.subOrg.contains(Long.parseLong(orgId));		
	}

}
