package com.yihuacomputer.fish.report.service.base;

import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.report.base.entity.StartPlan;
import com.yihuacomputer.fish.report.service.api.IDomainStartPlanService;

public abstract class DomainStartPlanService implements IDomainStartPlanService{
	@Override
    public IStartPlan make() {
        return new StartPlan();
    }
}
