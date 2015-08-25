/**
 * 
 */
package com.yihuacomputer.fish.api.advert;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 广告资源服务
 * 
 * @author xuxigang
 * 
 */
public interface IAdvertResourceService {
	public IAdvertResource make();

	public IAdvertResource getById(long id);

	public IAdvertResource add(IAdvertResource advert);

	public void update(IAdvertResource advert);

	public void delete(IAdvertResource advert);

	public void delete(long id);

	public List<IAdvertResource> list(IFilter filter);

	public IPageResult<IAdvertResource> page(int offset, int limit, IFilter filter);

}
