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
    /**
     * @return
     */
    ITransType make();
    /**
     * @param transCode
     * @param remark
     * @return
     */
    ITransType make(String transCode,String remark);
    /**
     * @param transType
     * @return
     */
    ITransType add(ITransType transType);
    /**
     * @param transType
     */
    void update(ITransType transType);
    /**
     * @param transTypeId
     */
    void delete(long transTypeId);
    /**
     * @param filter
     * @return
     */
    List<ITransType> list(IFilter filter);
    /**
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    IPageResult<ITransType> page(int start,int limit,IFilter filter);
    /**
     * 获取交易类型Map集合
     * key为交易码
     * @return
     */
    Map<String,ITransType> getTransTypeMap();
}
