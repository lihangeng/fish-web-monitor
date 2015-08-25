package com.yihuacomputer.fish.machine.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;
import com.yihuacomputer.fish.machine.entity.AtmGroup;

public abstract class DomainAtmGroupService implements IAtmGroupService{

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
}
