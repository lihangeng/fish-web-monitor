package com.yihuacomputer.fish.machine.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.machine.entity.AtmMove;
import com.yihuacomputer.fish.machine.service.api.IDomainAtmMoveService;

public abstract class DomainAtmMoveService implements IDomainAtmMoveService
{
    @Autowired
    private IGenericDao dao;

    @Override
    public AtmMove make() {
        return new AtmMove(this);
    }

    @Override
    public IPageResult<IAtmMove> page(int offset, int limit, IFilter filter) {
//        List<IAtmMove> lists = new ArrayList<IAtmMove>();
//        EntityUtils.convert(list(filter), lists);
//        return new PageResult<IAtmMove>(lists,offset,limit);
        return dao.page(offset, limit, filter, AtmMove.class);
    }
}
