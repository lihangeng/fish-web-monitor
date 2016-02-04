package com.yihuacomputer.fish.advert.bs.service;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.BsAdvertResource;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResourceService;

public class BsAdvertResourceService implements IBsAdvertResourceService {
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IBsAdvertResource make() {
		return new BsAdvertResource();
	}

	@Override
	public IBsAdvertResource save(IBsAdvertResource advert) {
		return dao.save(advert);
	}

	@Override
	public IBsAdvertResource update(IBsAdvertResource advert) {
		return dao.update(advert);
	}

	@Override
	public void deleteById(long id) {
		IBsAdvertResource advert = getById(id);
		if(null!=advert){
			delete(advert);
		}
	}

	@Override
	public void delete(IBsAdvertResource advert) {
		dao.delete(advert);
	}


	@Override
	public void delete(IBsAdvertResource advert,String fileName) {
		FileUtils.deleteQuietly(new File(fileName));
		dao.delete(advert);
	}
	
	@Override
	public IBsAdvertResource getById(long id) {
		return dao.get(id, BsAdvertResource.class);
	}

	@Override
	public IPageResult<IBsAdvertResource> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, BsAdvertResource.class);
	}

	@Override
	public List<IBsAdvertResource> list(IFilter filter) {
		return dao.findByFilter(filter, IBsAdvertResource.class);
	}

}
