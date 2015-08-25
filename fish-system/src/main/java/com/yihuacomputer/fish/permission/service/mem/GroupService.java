package com.yihuacomputer.fish.permission.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IGroupRoleRelation;
import com.yihuacomputer.fish.api.relation.IUserGroupRelation;
import com.yihuacomputer.fish.permission.entity.Group;
import com.yihuacomputer.fish.permission.service.api.IDomainGroupService;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-21
 */

@Service
public class GroupService extends BaseMemoryService implements
    IDomainGroupService{

	@Autowired
	private IOrganizationService organizationService;
//	@Autowired
	private IUserGroupRelation masterGroupRelation;
//	@Autowired
	private IGroupRoleRelation groupRoleRelation;

	private List<Group> entities = new ArrayList<Group>();

	@Override
	public IGroup make(String name) {
		Group group = new Group(name);
		group.setService(this);
		return group;
	}

	@Override
	public IGroup add(IGroup group) {
		//需要检查一下组名是否唯一
		Group entity = interface2Entity(group);
		entity.setId(nextId());
		entities.add(entity);
		return entity;
	}

	@Override
	public IGroup add(String name) {
		return add(make(name));
	}

	@Override
	public IGroup get(long id) {
		for(Group group : entities){
			if(group.getId()== id){
				return group;
			}
		}
		throw new NotFoundException(String.format("not found entity [group.id = %d]",id));
	}

	@Override
	public IGroup get(String name) {
		//是模糊匹配还是精确匹配？
		for(Group group  : entities){
			if(group.getName().equals(name)){
				return group;
			}
		}
		throw new NotFoundException(String.format("not found entity [group.name = %s]",name));
	}

	@Override
	public List<IGroup> list() {
		return EntityUtils.<IGroup>convert(entities);
	}

	@Override
	public List<IGroup> list(IFilter filter) {
		List<IGroup> result = new ArrayList<IGroup>();
		for(Group each : entities){
			if(isMacth(each,filter)){
				result.add(each);
			}
	   }
		return result;
	}

	@Override
	public IPageResult<IGroup> page(int offset, int limit, IFilter filter) {
		List<IGroup> lists = new ArrayList<IGroup>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IGroup>(lists,offset,limit);
	}

	public void remove(IGroup group) {
		entities.remove(group);
	}

	@Override
	public void remove(long id) {
		remove(get(id));
	}

	@Override
	public void remove(String code) {
		remove(get(code));
	}

	@Override
	public void update(IGroup group) {
		//需要检查组名是否唯一
	}

	@Override
	public List<IUser> listUser(IGroup group) {
		return masterGroupRelation.listUserByGroup(group);
	}

	@Override
	public List<IRole> listRole(IGroup group) {
		return groupRoleRelation.listRoleByGroup(group);
	}

//	@Override
//	public List<IUser> listMaster(IGroup group) {
//		for(Group each :entities){
//			if(each.getId() == group.getId()){
//				return each.listMaster();
//			}
//		}
//		return new ArrayList<IUser>();
//	}
//
//	@Override
//	public List<IRole> listRole(IGroup group) {
//		for(Group each :entities){
//			if(each.getId() == group.getId()){
//				return each.listRole();
//			}
//		}
//		return new ArrayList<IRole>();
//	}


}
