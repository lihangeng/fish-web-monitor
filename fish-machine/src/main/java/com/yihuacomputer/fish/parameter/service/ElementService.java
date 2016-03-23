package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IElement;
import com.yihuacomputer.fish.api.parameter.IElementService;
import com.yihuacomputer.fish.machine.entity.AtmVendor;
import com.yihuacomputer.fish.parameter.entity.Element;

public class ElementService implements IElementService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IElement make() {

		return new Element();
	}

	@Override
	public IElement get(long id) {

		return dao.get(id, Element.class);
	}

	@Override
	public IElement get(String name) {
		return null;
	}

	@Override
	public IElement add(IElement element) {

		return dao.save(element);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, Element.class);
	}

	@Override
	public void update(IElement element) {
		dao.update(interface2Entity(element,true));
	}

	private Element interface2Entity(IElement element, boolean load) {
		if (element instanceof Element) {
			return (Element) element;
		}
		return null;
	}

	@Override
	public Iterable<IElement> list() {

		return dao.loadAll(IElement.class);
	}

	@Override
	public IPageResult<IElement> page(int offset, int limit, IFilter filter) {

		return dao.page(offset, limit, filter, Element.class);
	}

	@Override
	public Iterable<IElement> list(IFilter filter) {

		return null;
	}

}
