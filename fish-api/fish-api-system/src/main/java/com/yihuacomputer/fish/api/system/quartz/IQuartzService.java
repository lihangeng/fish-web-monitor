package com.yihuacomputer.fish.api.system.quartz;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface IQuartzService {
	/**
	 * @param filter
	 * @return
	 */
	public List<Object[]> listJobs(IFilter filter);
	
}
