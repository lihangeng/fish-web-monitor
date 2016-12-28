package com.yihuacomputer.fish.relation.service.db;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.system.entity.RolePermissionObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述角色和权限间的关系实体
 *
 *
 * @date 2010-8-25
 *
 */
@Service
@Transactional
public class RolePermissionRelation implements IRolePermissionRelation {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public void link(IRole role, IPermission permission) {
        if (!haslink(role, permission)) {
            dao.save(RolePermissionObj.make(permission.getId(), role.getId()));
        }
    }

    @Override
    public void unlink(IRole role, IPermission permission) {
        RolePermissionObj obj = find(role, permission);
        if (obj != null) {
            dao.delete(obj);
        }
    }

    private RolePermissionObj find(IRole role, IPermission permission) {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("roleId", role.getId()));
        filter.addFilterEntry(FilterFactory.eq("permissionId", permission.getId()));
        RolePermissionObj obj = dao.findUniqueByFilter(filter, RolePermissionObj.class);
        return obj;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPermission> listPermissionByRole(IRole role) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Permission t , RolePermissionObj t1 ");
        hql.append("where t.id = t1.permissionId and t1.roleId = ?");
        if( role != null)
        {
        	List<IPermission> permissions = dao.findByHQL(hql.toString(), role.getId());
        	  return permissions;
        }
        else
        {
        	return null;
        }

    }

    /**
     * @param role
     * @return
     */
    @Transactional(readOnly = true)
    public List<IPermission> listUnlinkedPermissionByRole(IRole role) {
        List<IPermission> all = permissionService.list();
        return subtract(all, listPermissionByRole(role));
    }

    private List<IPermission> subtract(List<IPermission> minuend, List<IPermission> subtrahend) {
        List<IPermission> result = new ArrayList<IPermission>();
        for (IPermission item : minuend) {
            if (!subtrahend.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IRole> listRoleByPermission(IPermission permission) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Role t , RolePermissionObj t1 ");
        hql.append("where t.id = t1.roleId and t1.permissionId = ?");
        List<IRole> roles = dao.findByHQL(hql.toString(), permission.getId());
        return roles;
    }

    @Override
    public boolean haslink(IRole role, IPermission permission) {
        RolePermissionObj obj = find(role, permission);
        if (obj != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IPermission> pagePermissionByRole(int offset, int limit, IRole role) {
        List<IPermission> lists = listPermissionByRole(role);
        return new PageResult<IPermission>(lists, offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IPermission> pageUnlinkPermissionByRole(int offset, int limit, IRole role) {
        List<IPermission> lists = listPermissionByRole(role);
        return new PageResult<IPermission>(lists, offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPermission> listMenuByRole(IRole role) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Permission t , RolePermissionObj t1 ");
        hql.append("where t.id = t1.permissionId and t1.roleId = ? and t.isButton = ?");
        List<IPermission> permissions = dao.findByHQL(hql.toString(), role.getId(), false);
        return permissions;
    }
}
