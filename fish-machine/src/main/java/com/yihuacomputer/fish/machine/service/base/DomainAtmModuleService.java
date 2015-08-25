package com.yihuacomputer.fish.machine.service.base;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.machine.entity.AtmModule;
import com.yihuacomputer.fish.machine.service.api.IDomainAtmModuleService;

public abstract class DomainAtmModuleService implements IDomainAtmModuleService{

	@Override
	public AtmModule make() {
		return new AtmModule(this);
	}
	
	@Override
	public IPageResult<IAtmModule> page(int offset, int limit, IFilter filter) {
		List<IAtmModule> lists = new ArrayList<IAtmModule>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IAtmModule>(lists,offset,limit);
	}
}
