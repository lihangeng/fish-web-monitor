package com.yihuacomputer.fish.machine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.api.atmMove.IAtmMoveService;
import com.yihuacomputer.fish.machine.entity.AtmMove;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class AtmMoveService implements IAtmMoveService {
    @Autowired
    private IGenericDao dao;
    
    @Override
    public AtmMove make() {
        return new AtmMove();
    }

    @Override
    @Transactional(readOnly = true)
    public IAtmMove get(long id) {
        return dao.get(id, AtmMove.class);
    }

    @Override
    public IAtmMove add(IAtmMove atmMove) {
        return dao.save(atmMove);
    }

    @Override
    public void remove(long id) {
        dao.delete(id, AtmMove.class);
    }

    @Override
    public void update(IAtmMove atmMove) {
        dao.update(interface2Entity(atmMove, true));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IAtmMove> list() {
        return dao.loadAll(IAtmMove.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IAtmMove> list(IFilter filter) {
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
        List<IAtmMove> atms = dao.findByFilter(db, IAtmMove.class);
        List<IAtmMove> result = mem.filter(atms);
        return result;
    }

    private AtmMove interface2Entity(IAtmMove atmMove, boolean load) {
        if (atmMove instanceof AtmMove) {
            return (AtmMove) atmMove;
        }
        return null;
    }

	@Override
	public IPageResult<IAtmMove> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, AtmMove.class);
	}

}
