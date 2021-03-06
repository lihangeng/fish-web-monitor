package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.parameter.entity.AppSystem;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class AppSystemService implements IAppSystemService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IAppSystem get(long id) {
		return dao.get(id, AppSystem.class);
	}
	@Override
	public IAppSystem get(String name) {
		return (IAppSystem) dao.getCriteria(AppSystem.class).add(Restrictions.eq("name", name)).uniqueResult();
	}

	@Override
	public void update(IAppSystem appSystem) {
		dao.update(appSystem instanceof AppSystem ? (AppSystem) appSystem:null);
	}

	@Override
	public IPageResult<IAppSystem> page(int start, int limit, IFilter filter) {
		return dao.page(start, limit, filter, AppSystem.class);
	}

	@Override
	public List<IAppSystem> list() {
		return dao.loadAll(IAppSystem.class);
	}

	@Override
	public List<IAppSystem> getBelongs() {
		StringBuffer hql= new StringBuffer();
		hql.append("select t from AppSystem t");
		List<IAppSystem> result = dao.findByHQL(hql.toString());
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<IAppSystem> listContainsApp(IFilter filter) {
		return dao.findByFilter(filter, IAppSystem.class);
	}

}
