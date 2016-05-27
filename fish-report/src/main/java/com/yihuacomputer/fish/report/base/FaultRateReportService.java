package com.yihuacomputer.fish.report.base;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.FaultRateReport;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;

@Service
@Transactional
public class FaultRateReportService implements IFaultRateReportService {

	@Autowired
	private IGenericDao dao;

	@Override
	public Iterable<ITransactionMonths> typeTrans() {
		return dao.loadAll(ITransactionMonths.class);
	}


	@Override
	public Iterable<IEveryMonthFaultCount> typeFault() {
		return dao.loadAll(IEveryMonthFaultCount.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByBrand() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT t.tname,t.tcount,f.fcount FROM (SELECT trans.VENDOR_NAME tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.VENDOR_NAME)t LEFT JOIN ");
		sql.append("(SELECT fault.VENDOR_NAME fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault GROUP BY fault.VENDOR_NAME)f ");
		sql.append("ON t.tname = f.fname UNION ");
		sql.append("SELECT f.fname,t.tcount,f.fcount FROM (SELECT trans.VENDOR_NAME tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.VENDOR_NAME)t RIGHT JOIN ");
		sql.append("(SELECT fault.VENDOR_NAME fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault GROUP BY fault.VENDOR_NAME)f ON t.tname = f.fname");
		SQLQuery query= dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result= new ArrayList<FaultRateReport>();
		for(Object obj:list){
			FaultRateReport frr=new FaultRateReport();
			Object[] object=(Object[])obj;
			frr.setName(object[0]==null?"":String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1]==null?"0":String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2]==null?"0":String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByType() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT t.tname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.DEV_TYPE)t LEFT JOIN ");
		sql.append("(SELECT fault.DEV_TYPE fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault GROUP BY fault.DEV_TYPE)f ");
		sql.append("ON t.tname = f.fname UNION ");
		sql.append("SELECT f.fname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.DEV_TYPE)t RIGHT JOIN ");
		sql.append("(SELECT fault.DEV_TYPE fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault GROUP BY fault.DEV_TYPE)f ON t.tname = f.fname");
		SQLQuery query= dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result= new ArrayList<FaultRateReport>();
		for(Object obj:list){
			FaultRateReport frr=new FaultRateReport();
			Object[] object=(Object[])obj;
			frr.setName(object[0]==null?"":String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1]==null?"0":String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2]==null?"0":String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByModule() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT f.mname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans GROUP BY trans.DEV_TYPE)t RIGHT JOIN ");
		sql.append("(SELECT fault.DEV_MOD mname,fault.DEV_TYPE tname,SUM(fault.FAULT_COUNT) fcount ");
		sql.append("FROM CASE_FAULT_MONTH fault GROUP BY fault.DEV_MOD)f ON t.tname = f.tname ");
		SQLQuery query= dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result= new ArrayList<FaultRateReport>();
		for(Object obj:list){
			FaultRateReport frr=new FaultRateReport();
			Object[] object=(Object[])obj;
			frr.setName(object[0]==null?"":String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1]==null?"0":String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2]==null?"0":String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}


	

}
