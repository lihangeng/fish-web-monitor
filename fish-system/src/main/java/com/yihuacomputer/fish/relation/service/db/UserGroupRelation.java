package com.yihuacomputer.fish.relation.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IUserGroupRelation;
import com.yihuacomputer.fish.relation.entity.UserGroupObj;

/**
 * 维护主帐号和组之间的关系服务
 * 
 * @author SuYang
 * 
 * @date 2010-8-26
 * 
 */
@Service
@Transactional
public class UserGroupRelation implements IUserGroupRelation {
    @Autowired
    private IGenericDao dao;

    @Override
    public void link(IUser master, IGroup group) {
        dao.save(UserGroupObj.make(master.getId(), group.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IGroup> listGroupByUser(IUser master) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Group t , MasterGroupObj t1 ");
        hql.append("where t.id = t1.groupId and t1.masterId = ?");
        List<IGroup> groups = dao.findByHQL(hql.toString(), master.getId());
        return groups;
    }

    private List<IGroup> listUnlinkGroupByMaster(IUser master) {
        List<IGroup> allGropus = dao.findByHQL("from Group t where t.organizationCode = ?", master.getOrganization().getCode());
        return subtract(allGropus, listGroupByUser(master));
    }

    private List<IGroup> subtract(List<IGroup> minuend, List<IGroup> subtrahend) {
        List<IGroup> result = new ArrayList<IGroup>();
        for (IGroup item : minuend) {
            if (!subtrahend.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IUser> listUserByGroup(IGroup group) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Master t , MasterGroupObj t1 ");
        hql.append("where t.id = t1.masterId and t1.groupId = ?");
        List<IUser> masters = dao.findByHQL(hql.toString(), group.getId());
        return masters;
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IGroup> pageGroupByUser(int offset, int limit, IUser master) {
        List<IGroup> lists = listGroupByUser(master);
        return new PageResult<IGroup>(lists, offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IGroup> pageUnlinkGroupByUser(int offset, int limit, IUser master) {
        List<IGroup> lists = listUnlinkGroupByMaster(master);
        return new PageResult<IGroup>(lists, offset, limit);
    }

    @Override
    public void unlink(IUser master, IGroup group) {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("groupId", group.getId()));
        filter.addFilterEntry(FilterFactory.eq("masterId", master.getId()));
        UserGroupObj obj = dao.findUniqueByFilter(filter, UserGroupObj.class);
        if (obj != null) {
            dao.delete(obj);
        }
    }

    @Override
    public IPageResult<IUser> pageUserByGroup(int offset, int limit, IGroup group) {
        // TODO Auto-generated method stub
        return null;
    }

}
