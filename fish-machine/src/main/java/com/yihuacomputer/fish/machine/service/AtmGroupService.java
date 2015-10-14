package com.yihuacomputer.fish.machine.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;
import com.yihuacomputer.fish.machine.entity.AtmGroup;

@Service
@Transactional
public class AtmGroupService implements IAtmGroupService
{
    @Autowired
    private IGenericDao dao;
    
    @Override
	public AtmGroup make() {
		return new AtmGroup(this);
	}

	@Override
	public IPageResult<IAtmGroup> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, AtmGroup.class);
	}

    @Override
    @Transactional(readOnly = true)
    public IAtmGroup get(long id)
    {
        return dao.get(id, AtmGroup.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public IAtmGroup get(String name)
    {
    	IAtmGroup atmGroup = (IAtmGroup) dao
                .getCriteria(AtmGroup.class).add(Restrictions.eq("name", name))
                .uniqueResult();
        return atmGroup;
    }

    @Override
    public IAtmGroup add(IAtmGroup atmGroup)
    {
        return dao.save(atmGroup);
    }

    @Override
    public void remove(long id)
    {
    	dao.delete(id, AtmGroup.class);
    }

    @Override
    public void update(IAtmGroup atmGroup)
    {
    	dao.update(interface2Entity(atmGroup,true));
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmGroup> list()
    {
    	return dao.loadAll(IAtmGroup.class);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmGroup> list(IFilter filter)
    {
    	Filter db = new Filter();
    	Filter mem = new Filter();
    	for(IFilterEntry entry : filter.entrySet()){
    		if(entry.getKey().indexOf(".")>0){
    			mem.addFilterEntry(entry);
    		}else{
    			db.addFilterEntry(entry);
    		}
    	}
    	List<IAtmGroup> atms = dao.findByFilter(db, IAtmGroup.class);
    	return mem.filter(atms);
    }
    
    private AtmGroup interface2Entity(IAtmGroup atmGroup, boolean load) {
		if (atmGroup instanceof IAtmGroup) {
			return (AtmGroup) atmGroup;
		}
		return null;
	}

}
