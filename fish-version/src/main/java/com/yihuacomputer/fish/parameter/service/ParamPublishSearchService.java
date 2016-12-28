package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishSearchService;
import com.yihuacomputer.fish.parameter.entity.ParamPublish;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class ParamPublishSearchService implements IParamPublishSearchService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public IPageResult<IParamPublish> page(int start, int limit, IFilter outfilter) {
		IFilter filter = null;
        if(outfilter == null){
            filter = new Filter();
        }else{
            filter = outfilter;
        }
		return dao.page(start, limit, filter, ParamPublish.class);
	}

	@Override
	public Iterable<IParamPublish> list() {
		return dao.loadAll(IParamPublish.class);
	}

}
