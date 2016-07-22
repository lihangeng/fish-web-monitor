package com.yihuacomputer.fish.analysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.spring.DataSource;
import com.yihuacomputer.domain.spring.DataSources;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;

@Service
@Transactional
public class DeviceCatalogSummaryMonthService implements IDeviceCatalogSummaryMonthService {
	
	@Autowired 
	private IPersonService personService;
	
//	@Autowired
//	private IAnalysisDao dao;
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	@DataSource(value=DataSources.Monitor)
	public IDeviceCatalogSummaryMonth make() {
		List<IPerson> personList = dao.findByHQL("from Person", new Object[]{});
		System.out.println(personList.size());
		return null;
	}

	@Override
	public IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm) {
		List<IPerson> personList = personService.list(new Filter());
		System.out.println(personList.size());
		return null;
	}

	@Override
	@DataSource(value=DataSources.Analysis)
	public IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm) {
		List<IPerson> personList = dao.findByHQL("from Person", new Object[]{});
		System.out.println(personList.size());
		return null;
	}

	public List<IDeviceCatalogSummaryMonth> list(IFilter filter) {

		List<IPerson> personList = dao.findByHQL("from Person", new Object[]{});
		System.out.println(personList.size());
		return null;
	}

	@Override
	public IDeviceCatalogSummaryMonth get(String catalogName, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
