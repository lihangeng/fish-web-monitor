package com.yihuacomputer.fish.machine.service.db;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.machine.entity.AtmVendor;
import com.yihuacomputer.fish.machine.service.base.DomainAtmBrandService;

@Service
@Transactional
public class AtmBrandService extends DomainAtmBrandService
{
    @Autowired
    private IGenericDao dao;

    @Override
    @Transactional(readOnly = true)
    public IAtmVendor get(long id)
    {
        return dao.get(id, AtmVendor.class);
    }

    @Override
    public IAtmVendor add(IAtmVendor brand)
    {
        return dao.save(brand);
    }

    @Override
    public void remove(long id)
    {
    	dao.delete(id, AtmVendor.class);
    }

    @Override
    public void update(IAtmVendor brand)
    {
        dao.update(interface2Entity(brand,true));
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmVendor> list()
    {
    	return dao.loadAll(IAtmVendor.class);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmVendor> list(IFilter filter)
    {
        Filter db = new Filter();
        Filter mem = new Filter();
        for(IFilterEntry entry : filter.entrySet()){
        	if(entry.getKey().indexOf(".") > 0){
        		mem.addFilterEntry(entry);
        	}else{
        		db.addFilterEntry(entry);
        	}
        }
        List<IAtmVendor> atms = dao.findByFilter(db, IAtmVendor.class);
        return mem.filter(atms) ;
    }
    
	private AtmVendor interface2Entity(IAtmVendor brand, boolean load) {
		if (brand instanceof AtmVendor) {
			return (AtmVendor) brand;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public IAtmVendor get(String name) {
		IAtmVendor atmVendor = (IAtmVendor) dao.getCriteria(AtmVendor.class)
        .add(Restrictions.eq("name", name)).uniqueResult();
			return atmVendor;
	}

}
