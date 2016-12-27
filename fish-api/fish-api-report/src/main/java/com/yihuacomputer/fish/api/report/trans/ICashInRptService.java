package com.yihuacomputer.fish.api.report.trans;

import java.util.List;

import com.yihuacomputer.common.IFilter;


/**
 * @author YiHua
 *
 */
public interface ICashInRptService {
    
    /**
     * 根据条件获得加钞信息列表
     * @param filter
     * @return
     */
    public List<ICashInRpt> listCashInRpt(IFilter filter);

}
