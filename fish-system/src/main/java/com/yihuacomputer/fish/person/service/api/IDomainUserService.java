package com.yihuacomputer.fish.person.service.api;

import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.person.entity.User;

/**
 * 领域层Service接口
 * 
 * @author huxiaobao
 * 
 */
public interface IDomainUserService extends IUserService {

    /**
     * 格式化User
     * 
     * @param user
     * @return
     */
    public User convert(IUser user);

    /**
     * 根据账号获得账户信息
     */
    @Override
    public User get(String code);

    /**
     * 根据人员ID获得账户信息
     */
    public User getByPerson(String id);

    /**
     * 删除账户信息
     * 
     * @param user
     */
    public void remove(User user);

    /**
     * 获得人员服务接口
     * 
     * @return
     */
    public IPersonService getPersonService();

    /**
     * 迁移到目标组织
     * 
     * @param user
     * @param organization
     */
    public void move(IUser user, IOrganization organization);

}