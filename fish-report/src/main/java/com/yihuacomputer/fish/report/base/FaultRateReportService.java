package com.yihuacomputer.fish.report.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;

@Service
@Transactional
public class FaultRateReportService implements IFaultRateReportService {

	@Autowired
	private IGenericDao dao;

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<FaultRateReportForm> list() {
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT t.tname ,t.tcount,f.fcount FROM ");
//		sql.append("(SELECT trans.DEV_TYPE tname,sum(trans.TRANS_COUNT) tcount FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.DEV_TYPE) t, ");
//		sql.append("(SELECT fault.DEV_TYPE fname,sum(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault GROUP BY fault.DEV_TYPE) f ");
//		sql.append("WHERE t.tname = f.fname GROUP BY t.tname");
//		SQLQuery query=dao.getSQLQuery(sql.toString());
//		List<Object> fault=query.list();
//		List<FaultRateReportForm> list=new ArrayList<FaultRateReportForm>();
//		for(Object obj:fault){
//			Object[] object=(Object[])obj;
//			FaultRateReportForm fr=new FaultRateReportForm();
//			fr.setName(object[0]==null?"":String.valueOf(object[0]));
//			fr.setTradeCount(Long.parseLong(object[1]==null?"":String.valueOf(object[1])));
//			fr.setFaultCount(Long.parseLong(object[2]==null?"":String.valueOf(object[2])));
//			list.add(fr);
//		}
//		return list;
//	}


	@Override
	public Iterable<ITransactionMonths> typeTrans() {
		return dao.loadAll(ITransactionMonths.class);
	}


	@Override
	public Iterable<IEveryMonthFaultCount> typeFault() {
		return dao.loadAll(IEveryMonthFaultCount.class);
	}

}
