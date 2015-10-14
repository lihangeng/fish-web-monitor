package com.yihuacomputer.fish.machine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.machine.entity.AtmModule;

@Service
@Transactional
public class AtmModuleService implements IAtmModuleService {
    @Autowired
    private IGenericDao dao;

    private Map<String,IAtmModule> modules = new HashMap<String,IAtmModule>();
    
    @Override
	public AtmModule make() {
		return new AtmModule();
	}
	
    public void init(){
    	for(IAtmModule atmModule : this.list()){
    		modules.put(atmModule.getName(), atmModule);
    	}
    }

    public Map<String,IAtmModule> getModules(){
    	return this.modules;
    }

    @Override
    @Transactional(readOnly = true)
    public IAtmModule get(long id) {
        return dao.get(id, AtmModule.class);
    }

    @Override
    public IAtmModule add(IAtmModule module) {
        return dao.save(module);
    }

    @Override
    public void remove(long id) {
        dao.delete(id, AtmModule.class);
    }

    @Override
    public void update(IAtmModule module) {
        dao.update(interface2Entity(module, true));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IAtmModule> list() {
        return dao.loadAll(IAtmModule.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IAtmModule> list(IFilter filter) {
        Filter db = new Filter();
        Filter mem = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {
            if (entry.getKey().indexOf(".") > 0) {
                mem.addFilterEntry(entry);
            }
            else {
                db.addFilterEntry(entry);
            }
        }
        List<IAtmModule> atms = dao.findByFilter(db, IAtmModule.class);
        List<IAtmModule> result = mem.filter(atms);
        return result;
    }

    private AtmModule interface2Entity(IAtmModule module, boolean load) {
        if (module instanceof AtmModule) {
            return (AtmModule) module;
        }
        return null;
    }

    @Override
    public IPageResult<IAtmModule> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, AtmModule.class);
    }

}
