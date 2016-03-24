package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IClassify;
import com.yihuacomputer.fish.api.parameter.IClassifyService;
import com.yihuacomputer.fish.api.parameter.IElement;
import com.yihuacomputer.fish.parameter.entity.Classify;
import com.yihuacomputer.fish.parameter.entity.Element;

@Service
@Transactional
public class ClassifyService implements IClassifyService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IClassify make() {
		return new Classify();
	}

	@Override
	public IClassify get(long id) {
		return dao.get(id, Classify.class);
	}

	@Override
	public IClassify get(String name) {
		return null;
	}

	@Override
	public IClassify add(IClassify classify) {
		return dao.save(classify);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, Classify.class);
	}

	@Override
	public void update(IClassify classify) {
		dao.update(interface2Entity(classify, true));
	}

	private Classify interface2Entity(IClassify classify, boolean load) {
		if (classify instanceof Classify) {
			return (Classify) classify;
		}
		return null;
	}

	@Override
	public IPageResult<IClassify> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Classify.class);
	}

}
