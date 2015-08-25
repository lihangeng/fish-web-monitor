package com.yihuacomputer.fish.permission.service.db;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IGroupRoleRelation;
import com.yihuacomputer.fish.api.relation.IUserGroupRelation;
import com.yihuacomputer.fish.permission.entity.Group;
import com.yihuacomputer.fish.permission.service.api.IDomainGroupService;

/**
 * @author weizaiqin
 * @version
 * @date 2010-9-17
 */

@Service
@Transactional
public class GroupService implements IDomainGroupService {
	@Autowired(required = false)
	private IUserGroupRelation userGroupRelation;
	@Autowired(required = false)
	private IGroupRoleRelation groupRoleRelation;

	@Autowired
	private IGenericDao dao;
	@Override
	public IGroup add(String name) {
		IGroup group = this.make(name);
		return dao.save(interface2Entity(group,false));
	}

	@Override
	public IGroup add(IGroup group) {
		return dao.save(interface2Entity(group,false));
	}

	@Override
	@Transactional(readOnly=true)
	public IGroup get(long id) {
		return dao.load(id, Group.class);
	}

	@Override
	@Transactional(readOnly=true)
	public IGroup get(String name) {
		Group group = (Group)dao.getCriteria(Group.class)
		.add(Restrictions.eq("name", name)).uniqueResult();
		if(group == null){
			throw new NotFoundException(String.format("不存在组[%l]",name));
		}
		return group;
	}

	@Override
	public List<IGroup> list() {
		return dao.loadAll(IGroup.class);
	}

	@Override
	public List<IGroup> list(IFilter filter) {
		return dao.findByFilter(filter, IGroup.class);
	}

	@Override
	public IGroup make(String name) {
		Group group = new Group(name);
		group.setService(this);
		return group;
	}

	@Override
	public IPageResult<IGroup> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Group.class);
	}

	@Override
	public void remove(long id) {
		try {
			dao.delete(get(id));
		} catch(NotFoundException nfe){
			throw nfe;
		} catch(Exception ex){
			throw new ServiceException(String.format("删除组[%l]失败",id),ex);
		}
	}

	@Override
	public void remove(String name) {
		try {
			dao.delete(get(name));
		} catch(NotFoundException nfe){
			throw nfe;
		} catch(Exception ex){
			throw new ServiceException(String.format("删除组[%l]失败",name),ex);
		}

	}

	@Override
	public void update(IGroup group) {
		dao.update(interface2Entity(group,true));
	}

	private Group interface2Entity(IGroup group,boolean load){
		if(group instanceof Group){
			return (Group)group;
		}
		return null;
	}

	@Override
	public List<IUser> listUser(IGroup group) {
		return userGroupRelation.listUserByGroup(group);
	}

	@Override
	public List<IRole> listRole(IGroup group) {
		return groupRoleRelation.listRoleByGroup(group);
	}

}
