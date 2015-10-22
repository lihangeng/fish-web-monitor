package com.yihuacomputer.fish.permission.service.db;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.permission.service.api.IDomainRoleService;
import com.yihuacomputer.fish.system.entity.Role;

/**
 *
 */
@Service
@Transactional
public class RoleService implements IDomainRoleService {

	@Autowired(required = false)
	private IOrganizationService organizationService;
	@Autowired(required = false)
	private IRolePermissionRelation rolePermissionRelation;
	@Autowired
	private MessageSource messageSourceVersion;
	@Autowired
	private IGenericDao dao;

	@Override
	public IRole add(String name) {
		IRole role = make(name);
		return add(role);
	}

	@Override
	public IRole add(IRole role) {
		return dao.save(interface2Entity(role, false));
	}

	@Override
	@Transactional(readOnly = true)
	public IRole get(long id) {
		return dao.get(id, Role.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IRole get(String name) {
		Role role = (Role) dao.getCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
		if (role == null) {
			
			throw new NotFoundException(messageSourceVersion.getMessage("system.role.notExist", new Object[]{name}, FishCfg.locale));
		}
		return role;
	}

	@Override
    @Transactional(readOnly=true)
	public List<IRole> list() {
		return dao.loadAll(IRole.class);
	}

	@Override
    @Transactional(readOnly=true)
	public IRole make(String name) {
		Role role = new Role(name);
		role.setService(this);
		return role;
	}

	@Override
    @Transactional(readOnly=true)
	public IPageResult<IRole> page(int offset, int limit) {
		return page(offset, limit, new Filter());
	}

	@SuppressWarnings("deprecation")
	@Override
    @Transactional(readOnly=true)
	public IPageResult<IRole> page(int offset, int limit, IFilter filter) {
		filter.addFilterEntry(FilterFactory.eq("system", false));// 过滤掉系统内置账号
		return dao.page(offset, limit, filter, Role.class);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, Role.class);
	}

	@Override
	public void remove(String name) {
		IRole entity = get(name);
		dao.delete(entity.getId(), Role.class);
	}

	@Override
	public void update(IRole role) {
		dao.update(interface2Entity(role, true));
	}

	@Override
    @Transactional(readOnly=true)
	public List<IRole> list(IFilter filter) {
//		filter.addFilterEntry(FilterFactory.eq("system", false));
		return dao.findByFilter(filter, IRole.class);
	}

	public void remove(Role role) {
		dao.delete(role);
	}

	private Role interface2Entity(IRole role, boolean load) {
		if (role instanceof Role) {
			return (Role) role;
		}
		return null;
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
