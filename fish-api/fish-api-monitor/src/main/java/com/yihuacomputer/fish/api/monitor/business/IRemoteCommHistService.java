package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IRemoteCommHistService {

    /**
     * 创建实体
     *
     * @return
     */
    public IRemoteCommHist make();

    /**
     * 唯一获取
     *
     * @param id
     * @return
     */
    public IRemoteCommHist get(long id);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(long id);

    /**
     * 保存信息
     *
     * @param remoteCommHist
     */
    public void save(IRemoteCommHist remoteCommHist);

    /**
     * 修改信息
     *
     * @param remoteCommHist
     */
    public void update(IRemoteCommHist remoteCommHist);

    /**
     * 查询所有信息
     *
     * @return
     */
    public List<IRemoteCommHist> list();

    /**
     * 条件查询信息
     * @param filter
     * @return
     */
    public List<IRemoteCommHist> list(IFilter filter);

    /**分页
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IRemoteCommHist> page(int start, int limit, IFilter filter);

    /**
     * 
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<Object> pageObj(int start, int limit, IFilter filter);

}
