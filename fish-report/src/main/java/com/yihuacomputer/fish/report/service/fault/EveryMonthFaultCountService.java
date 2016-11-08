package com.yihuacomputer.fish.report.service.fault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.report.entity.EveryMonthFaultCount;

@Service
@Transactional
public class EveryMonthFaultCountService implements IEveryMonthFaultCountService {

	@Autowired
	private IGenericDao dao;

	@SaveMethodDescrible(isUpdate=true,keyName={"devTypeId","devMod","classifyId","faultDate"})
	@Override
	public void add(IEveryMonthFaultCount everyMonthFaultCount) {
		dao.save(everyMonthFaultCount);
	}
	@Override
	public IEveryMonthFaultCount make() {
		return new EveryMonthFaultCount();
	}

}
