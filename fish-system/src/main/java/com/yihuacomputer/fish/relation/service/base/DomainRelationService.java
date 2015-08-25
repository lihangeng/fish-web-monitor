package com.yihuacomputer.fish.relation.service.base;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.relation.IRelationService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;

/**
 * @author YiHua
 * 
 */
public abstract class DomainRelationService implements IRelationService {

    @Autowired
    private IUserRoleRelation userRoleRelation;

    @Autowired
    private IRolePermissionRelation rolePermissionRelation;

    @Override
    public Set<IPermission> findPermissonsByUser(long userId) {
        Set<IPermission> permissions = new HashSet<IPermission>();
        permissions.addAll(userRoleRelation.listUserPermission(userId));
        return permissions;
    }

    public IUserRoleRelation getUserRoleRelation() {
        return userRoleRelation;
    }

    public IRolePermissionRelation getRolePermissionRelation() {
        return rolePermissionRelation;
    }

}
