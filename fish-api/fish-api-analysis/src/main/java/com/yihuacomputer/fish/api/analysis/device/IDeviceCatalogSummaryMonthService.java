package com.yihuacomputer.fish.api.analysis.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IDeviceCatalogSummaryMonthService {
	IDeviceCatalogSummaryMonth make();
	IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm);
	IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm);
	List<IDeviceCatalogSummaryMonth> list(IFilter filter);
	IDeviceCatalogSummaryMonth get(String catalogName,String date);
}
