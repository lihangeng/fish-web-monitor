package com.yihuacomputer.fish.permission.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.permission.entity.Role;
import com.yihuacomputer.fish.permission.service.api.IDomainRoleService;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-24
 */
@Service
public class RoleService extends BaseMemoryService implements IDomainRoleService {

	private IRolePermissionRelation rolePermissionRelation;

	private List<Role> entities = new ArrayList<Role>();

	@Override
	public Role make(String name) {
		Role role = new Role(this);
		role.setName(name);
		return role;
	}

	@Override
	public Role add(IRole role) {
		Role entity = interface2Entity(role);
		role.setId(nextId());
		entities.add(entity);
		return entity;
	}

	@Override
	public Role add(String name) {
		return add(make(name));
	}

	@Override
	public Role get(long id) {
		for(Role role : entities) {
			if(id == role.getId()) {
				return role;
			}
		}
		throw new NotFoundException("role not found");
	}

	@Override
	public Role get(String name) {
		for(Role role : entities) {
			if(name.equals(role.getName())) {
				return role;
			}
		}
		throw new NotFoundException(String.format("角色【%s】不存在", name));
	}

	@Override
	public void update(IRole role) {
	}

	@Override
	public void remove(Role role) {
		entities.remove(role);
	}

	@Override
	public void remove(String name) {
		remove(get(name));
	}

	@Override
	public void remove(long id) {
		remove(get(id));
	}

	@Override
	public List<IRole> list() {
		return EntityUtils.<IRole>convert(entities);
	}

	@Override
	public IPageResult<IRole> page(int offset, int limit) {
		return page(offset,limit,new Filter());
	}

	public List<IRole> list(IFilter filter) {
		List<IRole> result = new ArrayList<IRole>();
		for(Role each : entities){
			if(isMacth(each,filter) && !each.isSystem()){
				result.add(each);
			}
	   }
		return result;
	}

	@Override
	public IPageResult<IRole> page(int offset, int limit, IFilter filter) {
		return new PageResult<IRole>(list(filter),offset,limit);
	}

	@Override
	public List<IPermission> listPermission(IRole role) {
		return rolePermissionRelation.listPermissionByRole(role);
	}

	@Override
	public IRole getByCode(String roleCode) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void remove(IRole irole) {
        // TODO Auto-generated method stub

    }

	@Override
	public IRole getByName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}


}
