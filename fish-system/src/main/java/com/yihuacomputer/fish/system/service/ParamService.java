package com.yihuacomputer.fish.system.service;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.system.entity.Param;

@Service
@Transactional
public class ParamService implements IParamService {

    @Autowired
    private IGenericDao dao;

	@Override
	public IParam getParam(String paramKey) {
		return (IParam) dao.getCriteria(Param.class).add(Restrictions.eq("paramKey", paramKey)).uniqueResult();
	}

	/**
	 * 初始化装载参数到FishCfg中
	 */
	public void init(){
		Iterable<IParam> parmList = this.list();
		for(IParam param:parmList){
			FishCfg.setFishCfg(param.getParamKey(), param.getParamValue());
		}
	}

	@Override
	public IParam get(long id) {
		return dao.get(id, Param.class);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, Param.class);
	}

	@Override
	public void remove(String paramKey) {
		IParam entity = getParam(paramKey);
		this.remove(entity.getId());
	}

	@Override
	public void update(IParam param) {
		dao.update(interface2Entity(param,true));
		FishCfg.setFishCfg(param.getParamKey(), param.getParamValue());
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<IParam> list() {
		return dao.loadAll(IParam.class);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<IParam> list(IFilter filter) {
      return dao.findByFilter(filter, IParam.class);
    }

	@Override
	public IParam add(IParam param) {
		return dao.save(this.interface2Entity(param,false));
	}

	private Param interface2Entity(IParam param, boolean load) {
		if (param instanceof Param) {
			return (Param) param;
		}
		return null;
	}


	@Override
	public IPageResult<IParam> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Param.class);
	}
	
	@Override
	public IParam make() {
		return new Param(this);
	}

	@Override
	public IParam add(String name, String value, String description) {
		IParam param = make();
		param.setParamKey(name);
		param.setParamValue(value);
		param.setDescription(description);
		this.add(param);
        FishCfg.setFishCfg(name, value);
		return param;
	}
	
	@Override
	public void reload() {
		
	}


}
