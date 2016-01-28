package com.yihuacomputer.fish.advert.bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.BsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;

@Service
@Transactional
public class BsAdvertService implements IBsAdvertService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public IBsAdvert make() {
		return new BsAdvert();
	}

	@Override
	public IBsAdvert save(IBsAdvert advert) {
		return dao.save(advert);
	}

	@Override
	public IBsAdvert update(IBsAdvert advert) {
		return dao.update(advert);
	}

	@Override
	public void deleteById(long id) {
		IBsAdvert advert = getById(id);
		if(null!=advert){
			delete(advert);
		}
	}

	@Override
	public void delete(IBsAdvert advert) {
		dao.delete(advert);
	}

	@Override
	public IBsAdvert getById(long id) {
		return dao.get(id, BsAdvert.class);
	}

	@Override
	public IPageResult<IBsAdvert> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, BsAdvert.class);
	}

	@Override
	public List<IBsAdvert> list(IFilter filter) {
		return dao.findByFilter(filter, IBsAdvert.class);
	}

}
