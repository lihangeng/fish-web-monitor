/**
 * 
 */
package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 交易类型service
 * @author xuxigang
 *
 */
public interface ITransTypeService {
    ITransType make();
    ITransType make(String transCode,String remark);
    ITransType add(ITransType transType);
    void update(ITransType transType);
    void delete(long transTypeId);
    List<ITransType> list(IFilter filter);
    IPageResult<ITransType> page(int start,int limit,IFilter filter);
    /**
     * 获取交易类型Map集合
     * key为交易码
     * @return
     */
    Map<String,ITransType> getTransTypeMap();
}
