package com.yihuacomputer.fish.machine.service.base;


import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.machine.entity.AtmVendor;
import com.yihuacomputer.fish.machine.service.api.IDomainAtmBrandService;

public abstract class DomainAtmBrandService implements IDomainAtmBrandService{
    @Autowired
    private IGenericDao dao;

	@Override
	public AtmVendor make() {
		return new AtmVendor(this);
	}

	@Override
	public IPageResult<IAtmVendor> page(int offset, int limit, IFilter filter) {
	    return dao.page(offset, limit, filter, AtmVendor.class);
	}
}
