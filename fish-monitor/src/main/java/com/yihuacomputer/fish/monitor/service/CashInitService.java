package com.yihuacomputer.fish.monitor.service;

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
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.monitor.entity.business.CashInit;

@Service
@Transactional
public class CashInitService implements ICashInitService {

    @Autowired
    private IGenericDao dao;

    @Override
    public void save(ICashInit cashInit) {
        this.dao.save(cashInit);
    }

    @Override
    public ICashInit load(String terminalId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICashInit make() {
        return new CashInit();
    }

    @Override
    public ICashInit get(long id) {
        return dao.get(id, CashInit.class);
    }

    @Override
    public ICashInit get(String code) {
        ICashInit cashinit = (ICashInit) dao.getCriteria(CashInit.class).add(Restrictions.eq("terminalId", code))
                .uniqueResult();
        return cashinit;
    }

    @Override
    public List<ICashInit> list() {
        return list(new Filter());
    }

    @Override
    public List<ICashInit> list(IFilter filter) {
        List<CashInit> deviceList = dao.findByFilter(filter, CashInit.class);
        return EntityUtils.<ICashInit> convert(deviceList);
    }

    @Override
    public IPageResult<ICashInit> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, CashInit.class);
    }

}
