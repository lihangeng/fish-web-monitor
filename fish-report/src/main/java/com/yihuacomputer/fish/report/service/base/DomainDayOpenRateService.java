package com.yihuacomputer.fish.report.service.base;

import com.yihuacomputer.fish.api.report.IDayOpenRate;
import com.yihuacomputer.fish.report.entity.DayOpenRate;
import com.yihuacomputer.fish.report.service.api.IDomainDayOpenRateService;

public abstract class DomainDayOpenRateService implements IDomainDayOpenRateService {

    @Override
    public IDayOpenRate make() {
        return new DayOpenRate();
    }

}
