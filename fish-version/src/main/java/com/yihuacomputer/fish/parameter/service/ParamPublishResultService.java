package com.yihuacomputer.fish.parameter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;

@Service
@Transactional
public class ParamPublishResultService implements IParamPublishResultService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public IParamPublishResult make() {
		return new ParamPublishResult();
	}

	@Override
	public IParamPublishResult save(IParamPublishResult publishResult) {
		return dao.save(publishResult);
	}

	@Override
	public IParamPublishResult get(long id) {
		return dao.get(id, IParamPublishResult.class);
	}

	@Override
	public IParamPublishResult update(IParamPublishResult publishResult) {
		return dao.update(publishResult);
	}

	@Override
	public IParamPublishResult update(long id, String ret) {
		IParamPublishResult paramPublishResult = this.get(id);
		paramPublishResult.setRet(ret);
		return dao.update(paramPublishResult);
	}

}
