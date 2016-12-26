package com.yihuacomputer.fish.api.person;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 人员岗位接口
 *
 * @author pengwenchao
 *
 */
public interface IPersonJobService {

    /**
     * 实例化人员岗位
     *
     * @return
     */
    public IPersonJob make();

    /**
     * 根据主键找到人员岗位信息
     *
     * @param id
     * @return
     */
    public IPersonJob get(long id);

    /**
     * 根据编号找到人员岗位信息
     *
     * @param code
     * @return
     */
    public IPersonJob get(String code);

    /**
     * 增加人员岗位信息
     *
     * @param personJob
     * @return
     */
    public void add(IPersonJob personJob);

    /**
     * 根据编号删除人员岗位信息
     *
     * @param code
     */
    public void remove(String code);

    /**
     * 删除人员岗位信息
     *
     * @param id
     */
    public void remove(long id);

    /**
     * 更新人员信息
     *
     * @param personJob
     */
    public void update(IPersonJob personJob);

    /**
     * 返回所有人员岗位列表
     *
     * @return
     */
    public List<IPersonJob> list();

    /**
     * 条件查找人员岗位信息，返回岗位列表
     *
     * @param filter
     * @return
     */
    public List<IPersonJob> list(IFilter filter);

    /**
     * 分页
     *
     * @param offset
     * @param limit
     * @return
     */
    public IPageResult<IPersonJob> page(int offset, int limit);

    /**
     * 条件分页
     *
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IPersonJob> page(int offset, int limit, IFilter filter);
}
