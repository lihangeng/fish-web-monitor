package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.parameter.entity.ParamElement;

@Service
@Transactional
public class ParamElementService implements IParamElementService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IParamElement make() {

		return new ParamElement();
	}

	@Override
	@Transactional(readOnly = true)
	public IParamElement get(long id) {

		return dao.get(id, ParamElement.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IParamElement get(String name) {
		return null;
	}

	@Override
	public IParamElement add(IParamElement element) {

		return dao.save(element);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, ParamElement.class);
	}

	@Override
	public void update(IParamElement element) {
		dao.update(interface2Entity(element,true));
	}

	private ParamElement interface2Entity(IParamElement element, boolean load) {
		if (element instanceof ParamElement) {
			return (ParamElement) element;
		}
		return null;
	}

	@Override
	public Iterable<IParamElement> list() {

		return dao.loadAll(IParamElement.class);
	}

	@Override
	public IPageResult<IParamElement> page(int offset, int limit, IFilter filter) {

		return dao.page(offset, limit, filter, ParamElement.class);
	}

	@Override
	public Iterable<IParamElement> list(IFilter filter) {

		return null;
	}

	@Override
	public List<IParamElement> getValue() {
		StringBuffer hql= new StringBuffer();
		hql.append("select t from ParamElement t");
		List<IParamElement> result = dao.findByHQL(hql.toString());
		return result;
	}

}
