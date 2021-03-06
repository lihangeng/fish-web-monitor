package com.yihuacomputer.fish.permission.service.db;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.system.entity.Permission;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class PermissionService implements IPermissionService {
	private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
	@Autowired
	private IGenericDao dao;
	@Autowired
	private MessageSource messageSourceVersion;
	@Override
	public IPermission add(String id,String code, String description,boolean isButton) {
		IPermission permission= make(id);
		permission.setCode(code);
		permission.setDescription(description);
		permission.setButton(isButton);
		return dao.save(permission);
	}

	@Override
	@Transactional(readOnly=true)
	public IPermission getById(String id) {
		return dao.load(id, Permission.class);
	}

	@Override
	@Transactional(readOnly=true)
	public IPermission get(String code) {
		Permission permission = (Permission)dao.getCriteria(Permission.class)
		.add(Restrictions.eq("code", code)).uniqueResult();
		if(permission == null){
			
			throw new NotFoundException(messageSourceVersion.getMessage("system.permission.notExist", new Object[]{code}, FishCfg.locale));
		}
		return permission;
	}

	@Override
	public IPermission getRoot() {
		return get("root");
	}

	@Override
    @Transactional(readOnly=true)
	public List<IPermission> list() {
		 return dao.loadAll(IPermission.class);
	}

	@Override
	public IPermission make(String id) {
		Permission permission = new Permission(id);
		permission.setId(id);
		permission.setService(this);
		return permission;
	}

	@Override
    @Transactional(readOnly=true)
	public IPageResult<IPermission> page(int offset, int limit) {
		return dao.page(offset, limit, new Filter(), Permission.class);
	}

	@Override
    @Transactional(readOnly=true)
	public IPageResult<IPermission> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Permission.class);
	}

	@Override
	public void removeById(String id) {
		try {
			dao.delete(getById(id));
		} catch(NotFoundException nfe){
			throw nfe;
		} catch(Exception ex){
			throw new ServiceException(messageSourceVersion.getMessage("system.permission.deleteFail", new Object[]{id}, FishCfg.locale),ex);
		}

	}

	@Override
	public void remove(String code) {
		try {
			dao.delete(get(code));
		} catch(NotFoundException nfe){
			throw nfe;
		} catch(Exception ex){
			throw new ServiceException(messageSourceVersion.getMessage("system.permission.deleteFail", new Object[]{code}, FishCfg.locale),ex);
		}

	}

	@Override
	public void remove(IPermission permission){
		dao.delete(permission);
	}

	@Override
	public void update(IPermission permission) {
		dao.update(interface2Entity(permission,true));
	}

	private Permission interface2Entity(IPermission permission,boolean load){
		if(permission instanceof Permission){
			return (Permission)permission;
		}
		return null;
	}

	@Override
	public IPermission add(String id,String code, String description,boolean isButton,IPermission parent) {
		IPermission permission= make(id);
		permission.setCode(code);
		permission.setDescription(description);
		permission.setButton(isButton);
		permission.setParent(parent);
		logger.debug("permission code=" + code);
		return dao.save(permission);
	}

	@Override
    @Transactional(readOnly=true)
	public List<IPermission> listChildByParentId(String parentId) {
		return dao.findByHQL("from Permission where parentId = ?", parentId);
	}

	@Override
	public IPermission add(IPermission permission) {
		return dao.save(interface2Entity(permission, false));
	}

	@Override
	public List<IPermission> getGroupSendByCode() {
		return dao.findByHQL("from Permission where code in ('noteGroupSend','groupSend','groupPerson') ");
	}

}
