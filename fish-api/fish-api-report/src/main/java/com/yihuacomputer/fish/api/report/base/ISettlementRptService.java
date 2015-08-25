package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface ISettlementRptService {
    
    /**
     * 根据条件获得清机信息列表
     * @param filter
     * @return
     */
    public List<ISettlementRpt> listSettlementRpt(IFilter filter);

}
