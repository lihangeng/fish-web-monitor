package com.yihuacomputer.fish.api.report;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.ITempDevRelation;

public interface ITempOpenPlanDevRelation {

	 public void link(IDeviceOpenPlan openPlan, Long Id);

	 public void unlink(IDeviceOpenPlan openPlan,  Long Id);

	 public IPageResult<IStartPlan> page(int offset, int limit, IFilter filter);

	 public List<IDeviceOpenPlan> listPlanByDevice(Long deviceId);

     public List<ITempDevRelation> listRelationByDevice(Long deviceId);




}
