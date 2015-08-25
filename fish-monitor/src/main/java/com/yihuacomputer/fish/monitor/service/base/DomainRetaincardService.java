package com.yihuacomputer.fish.monitor.service.base;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.monitor.entity.business.Retaincard;
import com.yihuacomputer.fish.monitor.service.api.IDomainRetaincardService;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-29 上午10:37:02
 * @version 类说明
 */
public abstract class DomainRetaincardService implements
		IDomainRetaincardService {
	public Retaincard make() {
		return new Retaincard(this);
	}
	
	public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,long orgId) {
		List<IRetaincard> lists = new ArrayList<IRetaincard>();
		EntityUtils.convert(listCardByOrgId(orgId,filter), lists);
		return new PageResult<IRetaincard>(lists, offset, limit);
	}
}
