package com.yihuacomputer.fish.report.service.db;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.report.base.entity.EveryMonthFaultCount;

@Service
@Transactional
public class EveryMonthFaultCountService implements
		IEveryMonthFaultCountService {

	@Autowired
	private IGenericDao dao;

	@Override
	public void add(IEveryMonthFaultCount everyMonthFaultCount) {

		dao.save(everyMonthFaultCount);
	}

	@Override
	public void extractMonthFault(String date) {

		StringBuffer sql = new StringBuffer();
		String currDate = date;
		String startDate = currDate + "01";
		String endDate = currDate + "31";
		sql.append("select ID,TERMINAL_ID,DEV_MOD,CLASSIFY_ID,FAULT_DATE,count(TERMINAL_ID) ");
		sql.append("from case_fault ");
		sql.append("where FAULT_DATE between '");
		sql.append(startDate);
		sql.append("' and '");
		sql.append(endDate);
		sql.append("' group by TERMINAL_ID,DEV_MOD,CLASSIFY_ID,FAULT_DATE ");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> info = query.list();
		for (Object object : info) {
			Object[] obj = (Object[]) object;
			EveryMonthFaultCount everyMonthFaultCount = new EveryMonthFaultCount();
			everyMonthFaultCount.setId(Long.valueOf(obj[0].toString()));
			everyMonthFaultCount.setTerminalId(obj[1].toString());
			everyMonthFaultCount.setDevMod(obj[2].toString());
			everyMonthFaultCount.setClassifyId(obj[3].toString());
			everyMonthFaultCount.setFaultDate(Long.valueOf(obj[4].toString()));
			everyMonthFaultCount.setFaultCount(Long.valueOf(obj[5].toString()));

			add(everyMonthFaultCount);

		}
	}

}
