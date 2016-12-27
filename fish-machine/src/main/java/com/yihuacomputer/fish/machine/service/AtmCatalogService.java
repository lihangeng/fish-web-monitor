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
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class AtmCatalogService implements IAtmCatalogService
{
    @Autowired
    private IGenericDao dao;
    
    @Override
	public AtmCatalog make() {
		return new AtmCatalog();
	}

	@Override
	public IPageResult<IAtmCatalog> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, AtmCatalog.class);
	}

    @Override
    @Transactional(readOnly = true)
    public IAtmCatalog get(long id)
    {
        return dao.get(id, AtmCatalog.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IAtmCatalog get(String no)
    {
        IAtmCatalog atmCatalog = (IAtmCatalog) dao
                .getCriteria(AtmCatalog.class).add(Restrictions.eq("no", no))
                .uniqueResult();
        return atmCatalog;
    }

    @Override
    public IAtmCatalog add(IAtmCatalog catalog)
    {
        return dao.save(catalog);
    }

    @Override
    public void remove(long id)
    {
    	dao.delete(id, AtmCatalog.class);
    }

    @Override
    public void remove(String no)
    {
    	IAtmCatalog entity = get(no);
    	dao.delete(entity.getId(), AtmCatalog.class);
    }

    @Override
    public void update(IAtmCatalog catalog)
    {
    	dao.update(interface2Entity(catalog,true));
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmCatalog> list()
    {
    	return dao.loadAll(IAtmCatalog.class);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<IAtmCatalog> list(IFilter filter)
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
    	List<IAtmCatalog> atms = dao.findByFilter(db, IAtmCatalog.class);
    	return mem.filter(atms);
    }
    
    private AtmCatalog interface2Entity(IAtmCatalog catalog, boolean load) {
		if (catalog instanceof AtmCatalog) {
			return (AtmCatalog) catalog;
		}
		return null;
	}

}
