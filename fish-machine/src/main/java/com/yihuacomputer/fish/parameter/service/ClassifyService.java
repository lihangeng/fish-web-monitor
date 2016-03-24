package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IClassify;
import com.yihuacomputer.fish.api.parameter.IClassifyService;
import com.yihuacomputer.fish.parameter.entity.Classify;

public class ClassifyService implements IClassifyService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IClassify make() {
		return new Classify();
	}

	@Override
	public IClassify get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClassify get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClassify add(IClassify classify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(IClassify classify) {
		// TODO Auto-generated method stub

	}

	@Override
	public IPageResult<IClassify> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Classify.class);
	}

}
