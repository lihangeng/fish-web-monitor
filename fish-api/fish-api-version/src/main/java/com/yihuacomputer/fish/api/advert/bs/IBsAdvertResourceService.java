package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IBsAdvertResourceService {

	public IBsAdvertResource make();

	public IBsAdvertResource save(IBsAdvertResource advert);

	public IBsAdvertResource update(IBsAdvertResource advert);

	public void deleteById(long id);

	public void delete(IBsAdvertResource advert);

	public IBsAdvertResource getById(long id);

	public IPageResult<IBsAdvertResource> page(int offset, int limit, IFilter filter);
	public List<IBsAdvertResource> list(IFilter filter);
	public void delete(IBsAdvertResource advert,String fileName);
}
