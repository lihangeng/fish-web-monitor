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
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.person.entity.Person;
import com.yihuacomputer.fish.person.service.base.DomainPersonService;

@Service
public class PersonService extends DomainPersonService{

	private BaseMemoryService memService = new BaseMemoryService();

	private List<Person> entities = new ArrayList<Person>();

	@Override
	public Person add(String name) {
		Person result = make();
		result.setName(name);
		return add(result);
	}

	private Person add(Person person) {
	    person.setGuid(String.valueOf(memService.nextId()));
		entities.add(person);
		return person;
	}

	@Override
	public Person add(IPerson person) {
		Person result = memService.interface2Entity(person);
		return add(result);
	}

	@Override
	public Person get(String guid) {
		for(Person person : entities){
			if(person.getGuid().equals(guid)){
				return person;
			}
		}
		throw new NotFoundException(String.format("not found entity [user.id = %s]",guid));
	}

	@Override
	public Iterable<IPerson> list() {
		return EntityUtils.<IPerson>convert(entities);
	}

	@Override
	public IPageResult<IPerson> page(int offset, int limit) {
		return EntityUtils.<IPerson>convert(new PageResult<Person>(entities, offset, limit));
	}

	@Override
	public void remove(String guid) {
		Person resul=get(guid);
		entities.remove(resul);
	}

	@Override
	public void update(IPerson person) {
	}

	@Override
	public IPageResult<IPerson> page(int offset, int limit, IFilter filter) {
		return new PageResult<IPerson>(list(filter), offset, limit);
	}

	@Override
	public List<IPerson> list(IFilter filter) {
		return EntityUtils.<IPerson>convert(filter.filter(this.entities));
	}

	@Override
	public IPageResult<IPerson> pageUnlinkUser(int offset, int limit,
			IFilter filter) {
		return null;
	}


	@Override
    public List<IPerson> getRoot() {
        List<IPerson> result = new ArrayList<IPerson>();
        for(IPerson each : entities){
            if(each.getOrganization() == null){
                result.add(each);
            }
        }
        return result;
    }

    @Override
    public IPageResult<IPerson> page(int offset, int limit, String org) {
        return null;
    }

    @Override
    public IPageResult<IPerson> page(int offset, int limit, String org,
            IFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public IOrganizationService getOrgService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPerson> getByOrg(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
