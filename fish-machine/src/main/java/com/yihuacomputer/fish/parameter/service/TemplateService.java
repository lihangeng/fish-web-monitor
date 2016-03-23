package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.ITemplate;
import com.yihuacomputer.fish.api.parameter.ITemplateService;
import com.yihuacomputer.fish.parameter.entity.Template;

@Service
@Transactional
public class TemplateService implements ITemplateService {
	
	@Autowired
	private IGenericDao dao;


	@Override
	public ITemplate make() {
		
		return new Template();
	
	}

	@Override
	public ITemplate get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITemplate get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITemplate add(ITemplate element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ITemplate element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPageResult<ITemplate> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Template.class);
	}}
