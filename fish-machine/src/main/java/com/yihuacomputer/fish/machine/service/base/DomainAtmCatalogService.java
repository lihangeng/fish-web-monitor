package com.yihuacomputer.fish.machine.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;

public abstract class DomainAtmCatalogService implements IAtmCatalogService{

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
}
