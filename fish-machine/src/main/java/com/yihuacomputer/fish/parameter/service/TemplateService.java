package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
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
		return dao.get(id, Template.class);
	}

	@Override
	public ITemplate get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITemplate add(ITemplate template) {
		
		return dao.save(template);
			
	}

	@Override
	public void remove(long id) {
		dao.delete(id,Template.class);
	}

	@Override
	public void update(ITemplate element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPageResult<ITemplate> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Template.class);
	}

	@Override
	public IPageResult<IDevice> pageUnlinkedDevice(int offset, int limit,ITemplate template, IFilter filter) {
			
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit,ITemplate template, IFilter filter) {
			
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDevice> listDeviceByTemplate(ITemplate template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void link(ITemplate template, IDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlink(ITemplate template, IDevice device) {
		// TODO Auto-generated method stub
		
	}}
