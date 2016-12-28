package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResultService;
import com.yihuacomputer.fish.parameter.entity.ParamPublishAppResult;


/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class ParamPublishAppResultService implements IParamPublishAppResultService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public IParamPublishAppResult make() {
		return new ParamPublishAppResult();
	}

	@Override
	public IParamPublishAppResult save(IParamPublishAppResult appResult) {
		return dao.save(appResult);
	}

	@Override
	public IParamPublishAppResult update(IParamPublishAppResult appResult) {
		return dao.update(appResult);
	}

	@Override
	public List<IParamPublishAppResult> list(long paramPublishResultId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(ParamPublishAppResult.class.getSimpleName()).append(" appResult ")
		.append(" where appResult.paramPublishResult.id =?");
		return dao.findByHQL(sb.toString(), new Object[]{paramPublishResultId});
	}
	
	@Override
	public IParamPublishAppResult get(long paramPublishResultId,String name){
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(ParamPublishAppResult.class.getSimpleName()).append(" appResult ")
		.append(" where appResult.paramPublishResult.id =? and appResult.appSystem.name=?");
		return dao.findUniqueByHql(sb.toString(), new Object[]{paramPublishResultId,name});
	}

}
