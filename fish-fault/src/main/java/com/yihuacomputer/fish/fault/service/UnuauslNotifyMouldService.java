package com.yihuacomputer.fish.fault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;

import com.yihuacomputer.fish.api.fault.IUnuauslNotifyMouldService;
import com.yihuacomputer.fish.api.fault.IUnusualNotifyMould;

import com.yihuacomputer.fish.fault.entity.UnusualNotifyMould;

@Service
@Transactional
public class UnuauslNotifyMouldService implements IUnuauslNotifyMouldService{

	@Autowired
	private IGenericDao dao;

	@Override
	public IUnusualNotifyMould make() {
		return new UnusualNotifyMould();
	}

	@Override
	public void save(IUnusualNotifyMould unusualNotifyMould) {
		this.dao.save(unusualNotifyMould);
		
	}

	@Override
	public void updateUnuauslNotifyMould(IUnusualNotifyMould unusualNotifyMould) {
		dao.update(interface2Entity(unusualNotifyMould,true));
		
	}
	
	private UnusualNotifyMould interface2Entity(IUnusualNotifyMould unusualNotifyMould, boolean load){
		if(unusualNotifyMould instanceof UnusualNotifyMould){
			return (UnusualNotifyMould) unusualNotifyMould;
		}
		return null;
	}

	@Override
	public List<IUnusualNotifyMould> listUnusualNotifyMould() {
		return dao.loadAll(IUnusualNotifyMould.class);
	}

	@Override
	public IPageResult<IUnusualNotifyMould> page(int offset, int limit,
			IFilter filter) {
	   return dao.page(offset, limit, filter, UnusualNotifyMould.class);
	}

	@Override
	public IUnusualNotifyMould getUnusualNotifyMouldById(long id) {
	   return dao.get(id, UnusualNotifyMould.class);
	}


}
