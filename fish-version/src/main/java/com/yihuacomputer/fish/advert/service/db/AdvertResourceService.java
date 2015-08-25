/**
 * 
 */
package com.yihuacomputer.fish.advert.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.entity.AdvertResource;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertResource;

/**
 * 广告资源的数据库版本实现
 * @author xuxigang
 *
 */
@Service
@Transactional
public class AdvertResourceService implements IDomainAdvertResourceService{
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IAdvertResource make() {
		return new AdvertResource();
	}

	@Override
	public IAdvertResource getById(long id) {
		return dao.get(id, IAdvertResource.class);
	}

	@Override
	public IAdvertResource add(IAdvertResource advert) {
		AdvertResource entity = this.interface2Entity(advert, false);
		dao.save(entity);
		return entity;
	}

	@Override
	public void update(IAdvertResource advert) {
		dao.update(this.interface2Entity(advert, true));
	}

	@Override
	public void delete(IAdvertResource advert) {
		this.delete(advert.getId());
	}

	@Override
	public void delete(long id) {
		dao.delete(id, AdvertResource.class);//这里要写具体类
	}

	@Override
	public List<IAdvertResource> list(IFilter filter) {
		return dao.findByFilter(filter, IAdvertResource.class);
	}

	@Override
	public IPageResult<IAdvertResource> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, AdvertResource.class);
	}

	private AdvertResource interface2Entity(IAdvertResource advert,boolean load){
		if(advert instanceof AdvertResource){
			return (AdvertResource)advert;
		}
		return null; 
	}

}
