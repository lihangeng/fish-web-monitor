package com.yihuacomputer.fish.api.monitor.xfs;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;

public interface IClassifyMonitorService {
	 public IPageResult<IClassifyReport> pageStatus(int offset, int limit, IClassifyModStatusFilter statusFilter);

}
