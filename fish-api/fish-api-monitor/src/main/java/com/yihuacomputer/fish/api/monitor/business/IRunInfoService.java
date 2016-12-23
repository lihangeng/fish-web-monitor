package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IRunInfoService {

    /**
     * 创建运行实体
     *
     * @return
     */
    public IRunInfo make();

    /**
     * 保存运行信息
     *
     * @param runInfo
     */
    public void save(IRunInfo runInfo);

    /**
     * 查询所有运行信息
     *
     * @return
     */
    public List<IRunInfo> list();

    /**
     * 条件查询运行信息
     * @param filter
     * @return
     */
    public List<IRunInfo> list(IFilter filter);

    /**带排序的分页
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IRunInfo> page(int start,int limit,IFilter filter);
}
