package com.yihuacomputer.fish.machine.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.machine.entity.AtmType;
import com.yihuacomputer.fish.machine.service.api.IDomainAtmTypeService;

public abstract class DomainAtmTypeService implements IDomainAtmTypeService{
    @Autowired
    private IGenericDao dao;

	@Override
	public AtmType make() {
		return new AtmType();
	}

	@Override
	public IPageResult<IAtmType> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, AtmType.class);
	}
}
