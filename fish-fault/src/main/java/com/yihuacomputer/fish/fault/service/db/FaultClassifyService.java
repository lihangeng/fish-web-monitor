package com.yihuacomputer.fish.fault.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.fault.entity.FaultClassify;

@Service
@Transactional
public class FaultClassifyService implements IFaultClassifyService {

	@Autowired
	private IGenericDao dao;
	
	public IFaultClassify make(){
		return new FaultClassify();
	}
	public void save(IFaultClassify faultClassify){
		this.dao.save(faultClassify);
	}
	@Override
	public IFaultClassify getFaultClassifyById(String classifyId) {
		return dao.get(classifyId, FaultClassify.class);
	}

	@Override
	public void updateFaultClassify(IFaultClassify faultClassify) {
		dao.update(faultClassify);
	}

	@Override
	public List<IFaultClassify> listFaultClassify() {
		return dao.loadAll(IFaultClassify.class);
	}

	@Override
	public IPageResult<IFaultClassify> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, FaultClassify.class);
	}

}
