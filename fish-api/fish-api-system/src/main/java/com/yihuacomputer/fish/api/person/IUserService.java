package com.yihuacomputer.fish.api.person;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 账户信息
 * 
 * @author huxiaobao
 * 
 */
public interface IUserService {

    /**
     * 创建账户
     * 
     * @return
     */
    public IUser make();

    /**
     * 根据ID获得账户信息
     */
    public IUser get(long id);

    /**
     * 根据编号获得账户信息
     */
    public IUser get(String code);

    /**
     * 根据人员ID获得账户信息
     */
    public IUser getByPerson(String id);

    /**
     * 根据编号和人员增加一条账户信息
     * 
     * @param code
     * @param person
     * @return
     */
    public IUser add(String code, IPerson person);

    /**
     * 增加一条账户信息
     * 
     * @param user
     * @return
     */
    public IUser add(IUser user);

    /**
     * 根据ID删除一条账户信息
     * 
     * @param id
     */
    public void remove(long id);

    /**
     * 根据编号删除一条账户信息
     * 
     * @param code
     */
    public void remove(String code);

    /**
     * 更新账户信息
     * 
     * @param user
     */
    public void update(IUser user);

    /**
     * 返回账户列表
     * 
     * @return
     */
    public Iterable<IUser> list();

    /**
     * 根据条件分页返回账户列表
     */
    public IPageResult<IUser> page(int offset, int limit, IFilter filter);
    
    /**
     * 根据条件深度查询某机构下的用户
     */
    public IPageResult<IUser> page(int offset, int limit, IFilter filter ,String orgId);
    /**
     * 根据条件返回账户列表
     * 
     * @param filter
     * @return
     */
    public Iterable<IUser> list(IFilter filter);
    
    /**
     * 登陆用户验证，不符合规则时抛出异常，正常时返回登陆用户
     * @param userCode -用户名
     * @param plainText -密码
     * @return
     */
    public IUser login(String userCode,String plainText);
    
    /**
     * 判断该人员是否已经创建用户
     * @param userCode
     * @param personGuid
     * @return
     */
    public boolean isExist(String personGuid);
    
    public IPersonService getPersonService();

}
