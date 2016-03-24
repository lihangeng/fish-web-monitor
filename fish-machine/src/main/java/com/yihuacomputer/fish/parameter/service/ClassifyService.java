package com.yihuacomputer.fish.parameter.service;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IClassify;
import com.yihuacomputer.fish.api.parameter.IClassifyService;
import com.yihuacomputer.fish.parameter.entity.Classify;

@Service
@Transactional
public class ClassifyService implements IClassifyService {

	@Autowired
	private IGenericDao dao;

	@Autowired
	private MessageSource messageSourceVersion;

	@Override
	public IClassify make() {
		return new Classify();
	}

	@Override
	public IClassify get(long id) {
		return dao.get(id, Classify.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IClassify get(String name) {
		Classify classify = (Classify) dao.getCriteria(Classify.class).add(Restrictions.eq("name", name)).uniqueResult();
		return classify;
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
