package com.yihuacomputer.fish.api.system.quartz;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IQuartzService {
	public List<Object[]> listJobs(IFilter filter);
	
}
