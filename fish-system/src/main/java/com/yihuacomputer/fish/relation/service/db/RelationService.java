package com.yihuacomputer.fish.relation.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.relation.service.base.DomainRelationService;

@Service
@Transactional(readOnly = true)
public class RelationService extends DomainRelationService {
     @Autowired
    private IGenericDao dao;
    
    @Override
    public List<String> findButtonsByUser(long userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select distinct t.code from Permission t , RolePermissionObj t1 ,Role role,UserRoleObj userRole ");
        hql.append("where t.id = t1.permissionId and t1.roleId = role.id and role.id = userRole.roleId and t.isButton=? and userRole.userId = ?");
        return  dao.findByHQL(hql.toString(),true,userId);
    }

}
