package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.parameter.entity.AppSystem;

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
	public void update(IAppSystem appSystem) {
		dao.update(appSystem instanceof AppSystem ? (AppSystem) appSystem:null);
	}
	
	@Override
	public IPageResult<IAppSystem> page(int start, int limit, IFilter filter) {
		return dao.page(start, limit, filter, AppSystem.class);
	}
	
}
