package com.yihuacomputer.fish.relation.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IUserRole;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IUserPermissionRelation;
import com.yihuacomputer.fish.system.entity.UserPermissionObj;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class UserPermissionRelation implements IUserPermissionRelation {

    @Autowired
    private IGenericDao dao;

    @Override
    public void link(IUser user, IPermission permission) {
        if (!haslink(user, permission)) {
            dao.save(UserPermissionObj.make(permission.getId(), user.getId()));
        }
    }

    @Override
    public void unlink(IUser user, IPermission permission) {
        UserPermissionObj obj = find(user, permission);
        if (obj != null) {
            dao.delete(obj);
        }
    }

    @Override
    public boolean haslink(IUser user, IPermission permission) {
        UserPermissionObj obj = find(user, permission);
        if (obj != null) {
            return true;
        }
        return false;
    }

    private UserPermissionObj find(IUser user, IPermission permission) {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("userId", user.getId()));
        filter.addFilterEntry(FilterFactory.eq("permissionId", permission.getId()));
        UserPermissionObj obj = dao.findUniqueByFilter(filter, UserPermissionObj.class);
        return obj;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPermission> listShortMenuByUser(IUser user) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Permission t , UserPermissionObj t1 ");
        hql.append("where t.id = t1.permissionId and t1.userId = ?");
        List<IPermission> permissions = dao.findByHQL(hql.toString(), user.getId());
        return permissions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPermission> listPermissionsByUser(long userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select DISTINCT permission from Role role, Permission permission,  ");
        hql.append("User user, RolePermissionObj rolePermission, UserRoleObj userRole ");
        hql.append("where user.id = userRole.userId and userRole.roleId = role.id ");
        hql.append("and role.id = rolePermission.roleId and permission.isButton = ? ");
        hql.append("and permission.id = rolePermission.permissionId ");
        hql.append("and user.id = ? ");
        List<IPermission> permissions = dao.findByHQL(hql.toString(), Boolean.FALSE,userId);
        return permissions;
    }

    @Override
    public List<IPermission> listPermissionsByUser(String roleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IUserRole> listRoleCodeByUserId(long userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
