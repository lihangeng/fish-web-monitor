package com.yihuacomputer.fish.parameter.service;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.parameter.entity.ParamClassify;

@Service
@Transactional
public class ParamClassifyService implements IParamClassifyService {

	@Autowired
	private IGenericDao dao;

	@Autowired
	private MessageSource messageSourceVersion;

	@Override
	public IParamClassify make() {
		return new ParamClassify();
	}

	@Override
	public IParamClassify get(long id) {
		return dao.get(id, ParamClassify.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IParamClassify get(String name) {
		ParamClassify classify = (ParamClassify) dao.getCriteria(ParamClassify.class).add(Restrictions.eq("name", name)).uniqueResult();
		return classify;
	}

	@Override
	public IParamClassify add(IParamClassify classify) {
		return dao.save(classify);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, ParamClassify.class);
	}

	@Override
    @Transactional(readOnly=true)
    public Iterable<IParamClassify> list()
    {
    	return dao.loadAll(IParamClassify.class);
    }

	@Override
	public void update(IParamClassify classify) {
		dao.update(interface2Entity(classify, true));
	}

	private ParamClassify interface2Entity(IParamClassify classify, boolean load) {
		if (classify instanceof ParamClassify) {
			return (ParamClassify) classify;
		}
		return null;
	}

	@Override
	public IPageResult<IParamClassify> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, ParamClassify.class);
	}

}
