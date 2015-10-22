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
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.monitor.entity.business.Settlement;

@Service
@Transactional
public class SettlementService implements ISettlementService {

    @Autowired
    private IGenericDao dao;

    @Override
    public void save(ISettlement settlement) {
        this.dao.save(settlement);
    }

    @Override
    public ISettlement load(String terminalId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISettlement make() {
    	return new Settlement();
    }

    @Override
    public ISettlement get(long id) {
        return dao.get(id, Settlement.class);
    }

    @Override
    public ISettlement get(String code) {
        ISettlement settlement = (ISettlement) dao.getCriteria(Settlement.class)
                .add(Restrictions.eq("terminalId", code)).uniqueResult();
        return settlement;
    }

    @Override
    public List<ISettlement> list() {
        return list(new Filter());
    }

    @Override
    public List<ISettlement> list(IFilter filter) {
        List<Settlement> settlementList = dao.findByFilter(filter, Settlement.class);
        return EntityUtils.<ISettlement> convert(settlementList);
    }

    @Override
    public IPageResult<ISettlement> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Settlement.class);
    }
}
