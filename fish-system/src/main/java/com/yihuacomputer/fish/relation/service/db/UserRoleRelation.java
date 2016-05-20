package com.yihuacomputer.fish.relation.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.system.entity.UserRoleObj;

/**
 * 维护主帐号与角色之间的关系服务
 * 
 * @author SuYang
 * 
 * @date 2010-8-25
 * 
 */
@Service
@Transactional
public class UserRoleRelation implements IUserRoleRelation {
    @Autowired
    private IGenericDao dao;

    @Override
    public void link(IUser user, IRole role) {
        dao.save(UserRoleObj.make(user.getId(), role.getId()));
    }

    @Override
    public void unlink(IUser user, IRole role) {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("userId", user.getId()));
        filter.addFilterEntry(FilterFactory.eq("roleId", role.getId()));
        UserRoleObj obj = dao.findUniqueByFilter(filter, UserRoleObj.class);
        if (obj != null) {
            dao.delete(obj);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<IUser> listUserByRole(IRole role) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from User t ,UserRoleObj t1 ");
        hql.append("where t.id = t1.userId and t1.roleId = ?");
        List<IUser> users = dao.findByHQL(hql.toString(), role.getId());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IRole> listRoleByUser(IUser user) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Role t ,UserRoleObj t1 ");
        hql.append("where t.id = t1.roleId and t1.userId = ?");
        List<IRole> roles = dao.findByHQL(hql.toString(), user.getId());
        return roles;
    }

    @Transactional(readOnly = true)
    public List<IPermission> listUserPermission(long userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct m from UserRoleObj ur,RolePermissionObj r,Permission m ");
        hql.append("where ur.userId=? and r.roleId=ur.roleId and m.isButton=? and m.id=r.permissionId ");
        hql.append("order by m.id");
        List<IPermission> permission = dao.findByHQL(hql.toString(), userId, false);
        return permission;
    }
    
    /**
	 * 根据用户ID和父类菜单权限ID获取直接的子菜单权限
	 * @param userId
	 * @param parentPermissionId 父类菜单权限ID
	 * @return
	 * @since 2.1.0.6
	 */
    @Transactional(readOnly = true)
	public List<IPermission> findDirectChildPermissionsByUser(long userId,String parentPermissionId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct m from UserRoleObj ur,RolePermissionObj r,Permission m ");
        hql.append("where ur.userId=? and r.roleId=ur.roleId and m.isButton=? and m.id=r.permissionId ");
        hql.append("and m.parentId = ? order by m.id");
        List<IPermission> permission = dao.findByHQL(hql.toString(), userId, false,parentPermissionId);
        return permission;
    }
    
	/**
	 * 根据用户ID和父类菜单权限ID获取所有子类的菜单权限
	 * @param userId
	 * @param parentPermissionId 父类菜单权限ID
	 * @return
	 *  @since 2.1.0.6
	 */
	public List<IPermission> findAllChildPermissionsByUser(long userId,String parentPermissionId){
		StringBuffer hql = new StringBuffer();
        hql.append("select distinct m from UserRoleObj ur,RolePermissionObj r,Permission m ");
        hql.append("where ur.userId=? and r.roleId=ur.roleId and m.isButton=? and m.id=r.permissionId ");
        hql.append("and m.parentId like ? order by m.id");
        List<IPermission> permission = dao.findByHQL(hql.toString(), userId, false,parentPermissionId + "%");
        return permission;
	}

    @Transactional(readOnly = true)
    private List<IRole> listRoleByUser(IUser user, IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Role t ,UserRoleObj t1 ");
        hql.append("where t.id = t1.roleId and t1.userId = ?");
        List<IRole> roles = dao.findByHQL(hql.toString(), user.getId());
        List<IRole> result = new ArrayList<IRole>();
        for (IRole role : roles) {
            if (filter.isMatch(role)) {
                result.add(role);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    private List<IRole> listUnlinkRoleByUser(IUser user, IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("from Role t where t.id not in ");
        hql.append("(select distinct t1.roleId from User u,UserRoleObj t1 ");
        hql.append("where u.id = t1.userId and u.id = ?)");

        List<IRole> roles = dao.findByHQL(hql.toString(), user.getId());
        List<IRole> result = new ArrayList<IRole>();
        for (IRole role : roles) {
            if (filter.isMatch(role)) {
                result.add(role);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IRole> pageRoleByUser(int offset, int limit, IUser user, IFilter filter) {
        List<IRole> lists = listRoleByUser(user, filter);
        return new PageResult<IRole>(lists, offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IRole> pageUnlinkRoleByUser(int offset, int limit, IUser user, IFilter filter) {
        List<IRole> lists = listUnlinkRoleByUser(user, filter);
        return new PageResult<IRole>(lists, offset, limit);
    }

}
