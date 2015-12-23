package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IProcessFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;
import com.yihuacomputer.fish.monitor.entity.filter.BoxStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ClassifyModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.NetStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ProcessFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RetaincardFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RunStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.StatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.TransationFilter;

@Service
@Transactional
public class FilterService implements IFilterService {

    @Autowired
    private IGenericDao dao;

    @Override
    public void updateStatusFilter(IStatusFilter filter) {
        this.dao.update(filter);
    }

    @Override
    public IStatusFilter makeStatusFilter() {
        IStatusFilter filter = new StatusFilter();
        filter.setBoxStatusFilter(new BoxStatusFilter());
        filter.setRunStattusFilter(new RunStatusFilter());
        filter.setNetStatusFilter(new NetStatusFilter());
        filter.setModStatusFilter(new ModStatusFilter());
        return filter;
    }

    @Override
    public IStatusFilter makeAndSaveStatusFilter(String userId) {
        IStatusFilter filter = new StatusFilter();
        filter.setUserId(userId);
        filter.setBoxStatusFilter(new BoxStatusFilter());
        filter.setRunStattusFilter(new RunStatusFilter());
        filter.setNetStatusFilter(new NetStatusFilter());
        filter.setModStatusFilter(new ModStatusFilter());
        this.dao.save(filter);
        return filter;
    }

    @Override
    public ITransationFilter makeTransactionFilter() {
        TransationFilter transationFilter = new TransationFilter();
        return transationFilter;
    }

    @Override
    public IProcessFilter makeProcessFilter() {
        ProcessFilter processFilter = new ProcessFilter();
        return processFilter;
    }

    @Override
    public IRetaincardFilter makeRetaincardFilter() {
        return new RetaincardFilter();
    }

    public IClassifyModStatusFilter makeClassifyModStatusFilter() {
        return new ClassifyModStatusFilter();
    }

    @Override
    public void save(IStatusFilter statusFilter) {
        dao.save(statusFilter);
    }

    @Override
    public void delete(IStatusFilter statusFilter) {
        dao.delete(statusFilter);
    }

    @Override
    public void delete(long id) {
        dao.delete(id, StatusFilter.class);
    }

    @Override
    public List<IStatusFilter> list() {
        return list(new Filter());
    }

    @Override
    public List<IStatusFilter> list(IFilter filter) {
        return dao.findByFilter(filter, IStatusFilter.class);
    }

    @Override
    public IStatusFilter get(long id) {
        return dao.get(id, StatusFilter.class);
    }

    @Override
    public List<IStatusFilter> loadUserStatusFilter(String userId) {
        IFilter filter = new Filter();
        filter.eq("userId", userId);

        return list(filter);
    }

    @Override
    public IPageResult<IStatusFilter> page(int offset, int limit, IFilter filter) {

        // String hql =
        // "select sf,o,at,ag from StatusFilter sf, Organization o, AtmType at, AtmGroup ag ";
        // hql +=
        // " where sf.devType=at.id and sf.orgId=o.id and sf.atmGroup=ag.id ";
        //
        // List<Object> arrayObj = new ArrayList<Object>();
        //
        // return (IPageResult<Object>) dao.page(offset, limit, filter, hql,
        // arrayObj.toArray());

        return dao.page(offset, limit, filter, StatusFilter.class);
    }

    @Override
    public IStatusFilter getByFilterName(String userId, String filterName) {

        IStatusFilter statusFilter = (IStatusFilter) dao.getCriteria(StatusFilter.class)
                .add(Restrictions.eq("userId", userId)).add(Restrictions.eq("filterName", filterName)).uniqueResult();
        return statusFilter;
    }

    // public IClassifyBoxStatusFilter makeClassifyBoxStatusFilter() {
    // return new ClassifyBoxStatusFilter();
    // }

    // public IClassifyNetStatusFilter makeClassifyNetStatusFilter() {
    // return new ClassifyNetStatusFilter();
    // }

}
