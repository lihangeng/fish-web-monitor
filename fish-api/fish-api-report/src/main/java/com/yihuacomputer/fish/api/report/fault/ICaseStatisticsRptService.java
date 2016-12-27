package com.yihuacomputer.fish.api.report.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;

/**
 * @author YiHua
 *
 */
public interface ICaseStatisticsRptService {
	/**
	 * @param filter
	 * @return
	 */
	public List<Object> caseStatisticsRank(IFilter filter) ;

}
