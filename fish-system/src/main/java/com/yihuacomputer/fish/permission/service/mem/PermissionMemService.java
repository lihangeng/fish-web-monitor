package com.yihuacomputer.fish.permission.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.permission.entity.Permission;
import com.yihuacomputer.fish.permission.service.api.IDomainPermissionService;

@Service
public class PermissionMemService extends BaseMemoryService implements IDomainPermissionService {

	private List<Permission> data = new ArrayList<Permission>();

	@Override
	public Permission make(String code) {
		Permission permission = new Permission(code);
//		permission.setId(nextId());
		permission.setService(this);
		return permission;
	}

	@Override
	public Permission getById(String id) {
		for(Permission item : data) {
			if(id.equals(item.getId())) {
				return item;
			}
		}
		throw new NotFoundException("child not found");
	}

	@Override
	public Permission get(String code) {
		for(Permission item : data) {
			if(code.equals(item.getCode())) {
				return item;
			}
		}
		throw new NotFoundException("child not found");
	}

	@Override
	public Permission getRoot() {
		return get("root");
	}

	@Override
	public Permission add(String id,String code, String description,boolean isButton) {
		return add(id,code,description,isButton,null);
	}

	@Override
	public List<IPermission> list() {
		return EntityUtils.<IPermission>convert(data);
	}

	@Override
	public IPageResult<IPermission> page(int offset, int limit) {
		return EntityUtils.<IPermission>convert(new PageResult<Permission>(data, offset, limit));
	}

	@Override
	public void removeById(String id) {
		remove(getById(id));
	}

	@Override
	public void remove(String code) {
		remove(get(code));
	}

	@Override
	public void remove(Permission permission) {
		data.remove(permission);
	}

	@Override
	public void update(IPermission permission) {
		Permission result = get(permission.getId());
		result.copyProperties(permission);
	}

	public List<IPermission> list(IFilter filter) {
		List<IPermission> result = new ArrayList<IPermission>();
		for(Permission each : data){
			if(isMacth(each,filter)){
				result.add(each);
			}
	   }
		return result;
	}

	@Override
	public IPageResult<IPermission> page(int offset, int limit, IFilter filter) {
		List<Permission> result = filter(data, filter);
		return EntityUtils.<IPermission>convert(new PageResult<Permission>(result, offset, limit));
	}

	@Override
	public Permission add(String id,String code, String description,boolean isButton, Permission parent) {
		Permission result = make(id);
		result.setCode(code);
		result.setDescription(description);
		result.setButton(isButton);
		result.setParent(parent);
		data.add(result);
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Permission> listChildByParentId(String parentId) {
		IFilter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("parent.id", parentId));
		List<Permission> result = new ArrayList<Permission>();
		for(Permission each : data){
			if(isMacth(each,filter)){
				result.add(each);
			}
	    }
		 return result;
	  }

	@Override
	public Permission add(IPermission permission) {
		Permission entity = interface2Entity(permission);
	//	permission.setId(nextId());
		data.add(entity);
		return entity;
	}

	@Override
	public List<IPermission> getGroupSendByCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
