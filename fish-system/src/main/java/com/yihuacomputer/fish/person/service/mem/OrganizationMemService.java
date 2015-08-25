package com.yihuacomputer.fish.person.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationListener;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.person.entity.Organization;
import com.yihuacomputer.fish.person.service.base.DomainOrganizationService;

@Service
public class OrganizationMemService extends DomainOrganizationService {

	private BaseMemoryService memService = new BaseMemoryService();

	private List<Organization> data = new ArrayList<Organization>();

	private  List<IOrganizationListener> orgListeners = new ArrayList<IOrganizationListener>();



	@Override
	public Organization add(String code, String name) {
		Organization result = make();
		result.setCode(code);
		result.setName(name);
		return add(result);
	}

	@Override
	public Organization add(String code, String name, Organization parent) {
		Organization organization = make();
		organization.setCode(code);
		organization.setName(name);
		organization.setParent(parent);
		organization.setGuid(String.valueOf(memService.nextId()));
		data.add(organization);
		return organization;
	}

	@Override
	public Organization add(IOrganization entity) {
		Organization result = memService.interface2Entity(entity);
		result.setGuid(String.valueOf(memService.nextId()));
		data.add(result);
		//维护双向关系
		Organization parent = memService.interface2Entity(result.getParent());
		if(parent != null){
			boolean hasChild = false;
			for(IOrganization each : parent.listChildren()){
				if(each.getCode().equalsIgnoreCase(result.getCode())){
					hasChild = true;
					break;
				}
			}
			if(!hasChild){
				parent.getChildren().add(result);
			}
		}
		return result;
	}

	@Override
	public Organization get(String guid) {
		for(Organization item : data) {
			if(guid.equals(item.getGuid())) {
				return item;
			}
		}
		throw new NotFoundException("entity not found");
	}

	@Override
	public IOrganization getByCode(String code) {
		return getByCode(code,OrganizationType.BANK);
	}

	@Override
	public void remove(String guid) {
		data.remove(get(guid));
	}

	@Override
	public void remove(IOrganization organization) {
		remove(organization.getGuid());
	}

	@Override
	public void removeByCode(String code) {
		data.remove(getByCode(code));
	}

	@Override
	public void update(IOrganization organization) {
		Organization o = get(organization.getGuid());
		o.copyProperties(organization);
	}

	@Override
	public Iterable<IOrganization> list() {
		return EntityUtils.<IOrganization>convert(data);
	}

	@Override
	public IPageResult<IOrganization> page(int offset, int limit) {
		return EntityUtils.<IOrganization>convert(new PageResult<Organization>(data, offset, limit));
	}

	@Override
	public IPageResult<IOrganization> page(int offset, int limit, IFilter filter) {
		List<Organization> result = memService.filter(data, filter);
		return EntityUtils.<IOrganization>convert(new PageResult<Organization>(result, offset, limit));
	}

	@Override
	public List<IOrganization> getRoot() {
		List<IOrganization> result = new ArrayList<IOrganization>();
		for(IOrganization each : data){
			if(each.getParent() == null){
				result.add(each);
			}
		}
		return result;
	}

    @Override
    public IOrganization getByName(String name)
    {
        for(Organization item : data) {
            if(name.equals(item.getName())) {
                return item;
            }
        }
        throw new NotFoundException("entity not found");
    }

    @Override
    public Iterable<IOrganization> list(IFilter filter)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<IOrganization> list(long orgId)
    {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List<Long> listSubOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public IPageResult<IOrganization> page(int offset, int limit,
            IFilter filter, String orgId) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public IOrganization getMinOrgLevel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrganization> listChildren(String parentOrgId,
			OrganizationType orgType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrganization> listSubOrg(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrganizationListener> getOrgListeners() {
		return this.orgListeners;
	}

	@Override
	public void addOrgListener(IOrganizationListener orgListener) {
		this.orgListeners.add(orgListener);
	}

	@Override
	public void removeOrgListener(IOrganizationListener orgListener) {
		this.orgListeners.remove(orgListener);
	}

	@Override
	public IOrganization getByCode(String code, OrganizationType orgType) {
		for(Organization item : data) {
			if(code.equals(item.getCode()) && orgType.equals(item.getOrganizationType())) {
				return item;
			}
		}
		throw new NotFoundException("entity not found");
	}

	@Override
	public List<Long> listParent(String parentOrgId,
			OrganizationType orgType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPerson> listByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrganization> listMatching(IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}


}
