package com.yihuacomputer.fish.api.monitor.business;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 黑名单卡管理服务接口
 * 
 * @author shixiaolong
 * 
 */
public interface IBlackListCardService {

    /**
     * 创建黑名单卡实例
     * 
     * @return
     */
    public IBlackListCard make();

    /**
     * 根据主键guid返回黑名单卡
     * 
     * @param id
     * @return
     */
    public IBlackListCard get(long id);

    /**
     * 增加一条黑名单卡信息
     * 
     * @param entity
     * @return
     */
    public IBlackListCard add(IBlackListCard entity);

    /**
     * 修改黑名单卡
     * 
     * @param entity
     */
    public void update(IBlackListCard entity);
    
    /**
     * 根据主键删除黑名单卡
     * 
     * @param id
     */
    public void remove(long id);

    /**
     * 删除黑名单卡
     * 
     * @param blackListCard
     */
    public void remove(IBlackListCard blackListCard);

    /**
     * 返回黑名单卡列表
     * 
     * @return
     */
    public Iterable<IBlackListCard> list();

    /**
     * 根据条件返回黑名单卡列表
     * 
     * @param filter
     *            条件
     * @return 机构列表
     */
    public Iterable<IBlackListCard> list(IFilter filter);

    /**
     * 分页
     * 
     * @param offset
     * @param limit
     * @return
     */
    public IPageResult<IBlackListCard> page(int offset, int limit);

    /**
     * 根据条件分页查询
     * 
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IBlackListCard> page(int offset, int limit,
            IFilter filter);

    /**
     * 根据cardNo,userName找到黑名单卡信息
     * 
     * @param cardNo
     * @param userName
     * @return
     */
    public IBlackListCard findByCardNoUserName(String cardNo, String userName);
    
    /**
     * 根据卡号得到黑名单卡的信息：
     * @param cardNo
     * @return
     */
    public IBlackListCard findByCardNo(String cardNo);

}
