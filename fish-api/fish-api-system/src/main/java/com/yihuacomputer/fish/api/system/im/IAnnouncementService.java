package com.yihuacomputer.fish.api.system.im;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 公告服务接口
 * 
 * @author shixiaolong
 * 
 */
public interface IAnnouncementService {

    /**
     * 创建公告实例
     * 
     * @return
     */
    public IAnnouncement make();

    /**
     * 根据主键guid返回公告
     * 
     * @param id
     * @return
     */
    public IAnnouncement get(long id);

    /**
     * 增加一条组织机构信息
     * 
     * @param entity
     * @return
     */
    public IAnnouncement add(IAnnouncement entity);

    /**
     * 根据主键删除组织机构
     * 
     * @param guid
     */
    public void remove(long guid);

    /**
     * 删除组织机构
     * 
     * @param announcement
     */
    public void remove(IAnnouncement announcement);

    /**
     * 更新组织机构信息
     * 
     * @param announcement
     */
    public void update(IAnnouncement announcement);

    /**
     * 返回组织机构列表
     * 
     * @return
     */
    public Iterable<IAnnouncement> list();

    /**
     * 分页
     * 
     * @param offset
     * @param limit
     * @return
     */
    public IPageResult<IAnnouncement> page(int offset, int limit);

    /**
     * 根据条件分页查询
     * 
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IAnnouncement> page(int offset, int limit, IFilter filter);

}
