package com.yihuacomputer.fish.report.base;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.report.base.FaultRateReport;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;
import com.yihuacomputer.fish.report.base.entity.EveryMonthFaultCount;
import com.yihuacomputer.fish.report.entity.TransactionMonths;

@Service
@Transactional
public class FaultRateReportService implements IFaultRateReportService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IAtmBrandService brandService;

	@Autowired
	private IAtmTypeService atmTypeService;

	@Override
	public Iterable<ITransactionMonths> typeTrans() {
		return dao.loadAll(ITransactionMonths.class);
	}

	@Override
	public Iterable<IEveryMonthFaultCount> typeFault() {
		return dao.loadAll(IEveryMonthFaultCount.class);
	}

	public List<FaultRateReport> listAllHql(String monthStr){
		long month = Long.parseLong(monthStr);
		List<FaultRateReport> list= new ArrayList<FaultRateReport>();
		//统计品牌交易信息
		StringBuffer monthTrans = new StringBuffer();
		monthTrans.append("select sum(transMonth.transCount),transMonth.vendorId from ").append(TransactionMonths.class.getSimpleName())
		.append(" transMonth where transMonth.transDate=? group by transMonth.vendorId");
		List<Object> transList = dao.findByHQL(monthTrans.toString(), new Object[]{month});
		Map<Long,Long> transMap = new HashMap<Long,Long>();
		for(Object objects:transList){
			Object[]obj = (Object[])objects;
			transMap.put(Long.parseLong(obj[1].toString()), Long.parseLong(obj[0].toString()));
		}
		//统计品牌故障信息
		StringBuffer monthFault = new StringBuffer();
		monthFault.append("select count(faultMonth.faultCount),faultMonth.vendorId from ").append(EveryMonthFaultCount.class.getSimpleName())
		.append(" faultMonth where faultMonth.faultDate=? group by faultMonth.vendorId");
		List<Object> faultList = dao.findByHQL(monthFault.toString(), new Object[]{month});

		Map<Long,Long> faultMap = new HashMap<Long,Long>();
		for(Object objects:faultList){
			Object[]obj = (Object[])objects;
			faultMap.put(Long.parseLong(obj[1].toString()), Long.parseLong(obj[0].toString()));
		}
//		品牌迭代组装信息
		IFilter filter = new Filter();
		filter.order("id");
		Iterable<IAtmVendor> iter=brandService.list(filter);
		Iterator<IAtmVendor> iterator =  iter.iterator();
		DecimalFormat df = new DecimalFormat("0.00");
		while(iterator.hasNext()){
			IAtmVendor atmVendor = iterator.next();
			FaultRateReport faultRateReport = new FaultRateReport();
			long vendorId = atmVendor.getId();
			faultRateReport.setVendorId(vendorId);
			faultRateReport.setName(atmVendor.getName());
			long faultCount = faultMap.get(vendorId)==null?0l:faultMap.get(vendorId);
			faultRateReport.setFaultCount(faultCount);
			long transCount = transMap.get(vendorId)==null?0l:transMap.get(vendorId);
			faultRateReport.setTradeCount(transCount);
			if(transCount!=0l){
				faultRateReport.setRate(df.format((double) faultRateReport.getFaultCount() / faultRateReport.getTradeCount() * 100));
			}
			else{
				if(faultRateReport.getFaultCount()==0){
					faultRateReport.setRate("0.00");
				}else{
					faultRateReport.setRate("100.00");
				}
			}
			list.add(faultRateReport);
		}
		return list;
	}
	
	
	public List<FaultRateReport> listByVendorHql(String monthStr,long vendorId){
		long month = Long.parseLong(monthStr);
		List<FaultRateReport> list= new ArrayList<FaultRateReport>();
		//统计品牌交易信息
		StringBuffer monthTrans = new StringBuffer();
		monthTrans.append("select sum(transMonth.transCount),transMonth.devTypeId from ").append(TransactionMonths.class.getSimpleName())
		.append(" transMonth where transMonth.transDate=? and transMonth.vendorId=? group by transMonth.devTypeId");
		List<Object> transList = dao.findByHQL(monthTrans.toString(), new Object[]{month, vendorId});
		Map<Long,Long> transMap = new HashMap<Long,Long>();
		for(Object objects:transList){
			Object[]obj = (Object[])objects;
			transMap.put(Long.parseLong(obj[1].toString()), Long.parseLong(obj[0].toString()));
		}
		//统计品牌故障信息
		StringBuffer monthFault = new StringBuffer();
		monthFault.append("select count(faultMonth.faultCount),faultMonth.devTypeId from ").append(EveryMonthFaultCount.class.getSimpleName())
		.append(" faultMonth where faultMonth.faultDate=? and faultMonth.vendorId=?  group by faultMonth.devTypeId");
		List<Object> faultList = dao.findByHQL(monthFault.toString(), new Object[]{month, vendorId});

		Map<Long,Long> faultMap = new HashMap<Long,Long>();
		for(Object objects:faultList){
			Object[]obj = (Object[])objects;
			faultMap.put(Long.parseLong(obj[1].toString()), Long.parseLong(obj[0].toString()));
		}
//		品牌迭代组装信息
		IFilter filter = new Filter();
		filter.order("id");
		List<IAtmType> atmTypeList=atmTypeService.list(filter);
		DecimalFormat df = new DecimalFormat("0.00");
		for(IAtmType atmType:atmTypeList){
			FaultRateReport faultRateReport = new FaultRateReport();
			faultRateReport.setVendorId(vendorId);
			long atmTypeId = atmType.getId();
			faultRateReport.setDevTypeId(atmTypeId);
			faultRateReport.setName(atmType.getName());
			long faultCount = faultMap.get(atmTypeId)==null?0l:faultMap.get(atmTypeId);
			faultRateReport.setFaultCount(faultCount);
			long transCount = transMap.get(atmTypeId)==null?0l:transMap.get(atmTypeId);
			faultRateReport.setTradeCount(transCount);
			if(transCount!=0l){
				faultRateReport.setRate(df.format((double) faultRateReport.getFaultCount() / faultRateReport.getTradeCount() * 100));
			}
			else{
				if(faultRateReport.getFaultCount()==0){
					faultRateReport.setRate("0.00");
				}else{
					faultRateReport.setRate("100.00");
				}
			}
			list.add(faultRateReport);
		}
		return list;
	}
	
	public List<FaultRateReport> listByDevTypeHql(String monthStr,long vendorId,long devTypeId){
		long month = Long.parseLong(monthStr);
		List<FaultRateReport> list= new ArrayList<FaultRateReport>();
		//统计品牌交易信息
		StringBuffer monthTrans = new StringBuffer();
		monthTrans.append("select sum(transMonth.transCount) from ").append(TransactionMonths.class.getSimpleName())
		.append(" transMonth where transMonth.transDate=? and transMonth.vendorId=? and transMonth.devTypeId=? ");
		List<Object> transList = dao.findByHQL(monthTrans.toString(), new Object[]{month});
		long transCount=0;
		if(null!=transList&&transList.size()>0){
			Object obj = transList.get(transList.size()-1);
			transCount = (null==obj?0l:Long.parseLong(obj.toString()));
		}
		//统计品牌故障信息
		StringBuffer monthFault = new StringBuffer();
		monthFault.append("select count(faultMonth.faultCount),faultMonth.devMod from ").append(EveryMonthFaultCount.class.getSimpleName())
		.append(" faultMonth where faultMonth.faultDate=? and transMonth.devTypeId=? group by faultMonth.devMod ");
		List<Object> faultList = dao.findByHQL(monthFault.toString(), new Object[]{month});

		DecimalFormat df = new DecimalFormat("0.00");
		for(Object objects:faultList){
			FaultRateReport faultRateReport = new FaultRateReport();
			Object[]obj = (Object[])objects;
			faultRateReport.setVendorId(vendorId);
			faultRateReport.setDevTypeId(devTypeId);
			faultRateReport.setName(obj[1].toString());
			long faultCount = obj[0]==null?0l:Long.parseLong(obj[0].toString());
			faultRateReport.setFaultCount(faultCount);
			faultRateReport.setTradeCount(transCount);
			if(transCount!=0l){
				faultRateReport.setRate(df.format((double) faultRateReport.getFaultCount() / faultRateReport.getTradeCount() * 100));
			}
			else{
				if(faultRateReport.getFaultCount()==0){
					faultRateReport.setRate("0.00");
				}else{
					faultRateReport.setRate("100.00");
				}
			}
			list.add(faultRateReport);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByBrand(String time) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.tname,t.tcount,f.fcount FROM (SELECT trans.VENDOR_NAME tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans ");
		if (time != null) {
			sql.append("WHERE trans.TRANS_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY trans.VENDOR_NAME)t LEFT JOIN ");
		sql.append("(SELECT fault.VENDOR_NAME fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault ");
		if (time != null) {
			sql.append("WHERE fault.FAULT_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY fault.VENDOR_NAME)f ON t.tname = f.fname UNION ");
		sql.append("SELECT f.fname,t.tcount,f.fcount FROM (SELECT trans.VENDOR_NAME tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans ");
		if (time != null) {
			sql.append("WHERE trans.TRANS_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY trans.VENDOR_NAME)t RIGHT JOIN ");
		sql.append("(SELECT fault.VENDOR_NAME fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault ");
		if (time != null) {
			sql.append("WHERE fault.FAULT_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY fault.VENDOR_NAME)f ON t.tname = f.fname");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result = new ArrayList<FaultRateReport>();
		for (Object obj : list) {
			FaultRateReport frr = new FaultRateReport();
			Object[] object = (Object[]) obj;
			frr.setName(object[0] == null ? "" : String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1] == null ? "0" : String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2] == null ? "0" : String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByType(String name, String time) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.tname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans WHERE trans.VENDOR_NAME = '").append(name).append("'");
		if (time != null) {
			sql.append(" AND trans.TRANS_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY trans.DEV_TYPE)t LEFT JOIN ");
		sql.append("(SELECT fault.DEV_TYPE fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault WHERE fault.VENDOR_NAME = '").append(name).append("'");
		if (time != null) {
			sql.append(" AND fault.FAULT_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY fault.DEV_TYPE)f ON t.tname = f.fname UNION ");
		sql.append("SELECT f.fname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans WHERE trans.VENDOR_NAME = '").append(name).append("'");
		if (time != null) {
			sql.append(" AND trans.TRANS_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY trans.DEV_TYPE)t RIGHT JOIN ");
		sql.append("(SELECT fault.DEV_TYPE fname,SUM(fault.FAULT_COUNT) fcount FROM CASE_FAULT_MONTH fault WHERE fault.VENDOR_NAME = '").append(name).append("'");
		if (time != null) {
			sql.append(" AND fault.FAULT_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY fault.DEV_TYPE)f ON t.tname = f.fname");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result = new ArrayList<FaultRateReport>();
		for (Object obj : list) {
			FaultRateReport frr = new FaultRateReport();
			Object[] object = (Object[]) obj;
			frr.setName(object[0] == null ? "" : String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1] == null ? "0" : String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2] == null ? "0" : String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaultRateReport> listByModule(String name, String time) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT f.mname,t.tcount,f.fcount FROM (SELECT trans.DEV_TYPE tname,SUM(trans.TRANS_COUNT) tcount ");
		sql.append("FROM ATMC_TRANSACTION_MONTHS trans WHERE trans.DEV_TYPE = '").append(name).append("'");
		if (time != null) {
			sql.append(" AND trans.TRANS_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY trans.DEV_TYPE)t RIGHT JOIN ");
		sql.append("(SELECT fault.DEV_MOD mname,fault.DEV_TYPE tname,SUM(fault.FAULT_COUNT) fcount ");
		sql.append("FROM CASE_FAULT_MONTH fault WHERE fault.DEV_TYPE ='").append(name).append("'");
		if (time != null) {
			sql.append(" AND fault.FAULT_DATE =").append(Long.valueOf(time));
		}
		sql.append(" GROUP BY fault.DEV_MOD)f ON t.tname = f.tname ");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<Object> list = query.list();
		List<FaultRateReport> result = new ArrayList<FaultRateReport>();
		for (Object obj : list) {
			FaultRateReport frr = new FaultRateReport();
			Object[] object = (Object[]) obj;
			frr.setName(object[0] == null ? "" : String.valueOf(object[0]));
			frr.setTradeCount(Long.valueOf(object[1] == null ? "0" : String.valueOf(object[1])));
			frr.setFaultCount(Long.valueOf(object[2] == null ? "0" : String.valueOf(object[2])));
			result.add(frr);
		}
		return result;
	}

	@Override
	public List<IAtmType> getType(String name) {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT type FROM AtmVendor brand, AtmType type WHERE brand.id = type.devVendor.id AND brand.name =?");
		return dao.findByHQL(hql.toString(), name);
	}

	@Override
	public List<IAtmModule> getModule(String name) {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT module FROM AtmType type, AtmModule module,AtmTypeAtmModuleRelation relation ");
		hql.append("WHERE type.id = relation.atmTypeId AND module.id = relation.atmModuleId AND type.name =?");
		return dao.findByHQL(hql.toString(), name);
	}

}
