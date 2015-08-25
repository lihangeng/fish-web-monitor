package com.yihuacomputer.fish.person.service.db;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.person.entity.PersonJob;
import com.yihuacomputer.fish.person.service.base.DomainPersonJobService;

/**
 * 数据库版PersonJobService实现：
 *
 * @author pengwenchao
 *
 */
@Service
@Transactional
public class PersonJobService extends DomainPersonJobService {

    @Autowired
    private IGenericDao dao;

    @Override
    public IPersonJob get(long id) {
        return dao.get(id, PersonJob.class);
    }

    @Override
    public IPersonJob get(String code) {
        IPersonJob ersonJob = (IPersonJob) dao.getCriteria(PersonJob.class).add(Restrictions.eq("code", code))
                .uniqueResult();
        return ersonJob;
    }

    @Override
    public void add(IPersonJob personJob) {
        dao.save(personJob);
    }

    @Override
    public void remove(String code) {
        remove(get(code).getId());
    }

    @Override
    public void remove(long id) {
        dao.delete(id, PersonJob.class);
    }

    @Override
    public void update(IPersonJob personJob) {
        dao.update(personJob);
    }

    @Override
    public List<IPersonJob> list() {
        return list(new Filter());
    }

    @Override
    public List<IPersonJob> list(IFilter filter) {
        List<PersonJob> listPersonJob = dao.findByFilter(filter, PersonJob.class);
        return EntityUtils.<IPersonJob> convert(listPersonJob);
    }

    @Override
    public IPageResult<IPersonJob> page(int offset, int limit) {
        return page(offset, limit, new Filter());
    }

    @Override
    public IPageResult<IPersonJob> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, PersonJob.class);
    }

}
