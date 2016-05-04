package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetailService;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDetail;

public class ParamTemplateDetailService implements IParamTemplateDetailService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IParamTemplateDetail get(long id) {
		return dao.get(id, ParamTemplateDetail.class);
	}

	@Override
	public IPageResult<IParamTemplateDetail> page(int start, int limit,
			IFilter filter) {
		return dao.page(start, limit, filter, ParamTemplateDetail.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IParamTemplateDetail> getByDeviceId(int start,int limit,IFilter filter,long deviceId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select ptd from ParamTemplateDetail ptd,ParamTemplateDeviceRelation ptdr ");
		hql.append("where ptd.paramTemplate.id= ptdr.templateId and ptdr.deviceId = ?");
		return (IPageResult<IParamTemplateDetail>) dao.page(start, limit, filter, hql.toString(),Long.valueOf(deviceId));
	}

	@Override
	public List<IParamTemplateDetail> list(long deviceId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select ptd from ParamTemplateDetail ptd,ParamTemplateDeviceRelation ptdr ");
		hql.append("where ptd.paramTemplate.id= ptdr.templateId and ptdr.deviceId = ?");
		return dao.findByHQL(hql.toString(),Long.valueOf(deviceId));
	}

	@Override
	public List<IParamTemplateDetail> listByTempateId(long templateId) {
		return dao.loadAll(IParamTemplateDetail.class);
	}




}
