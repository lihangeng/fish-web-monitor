package com.yihuacomputer.fish.report.service.fault.etl;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultClassifyWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultDurationWeek;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultExtractDataService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultMonth;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultWeek;
import com.yihuacomputer.fish.report.entity.etl.FaultClassifyMonth;
import com.yihuacomputer.fish.report.entity.etl.FaultClassifyWeek;
import com.yihuacomputer.fish.report.entity.etl.FaultDurationMonth;
import com.yihuacomputer.fish.report.entity.etl.FaultDurationWeek;
import com.yihuacomputer.fish.report.entity.etl.FaultMonth;
import com.yihuacomputer.fish.report.entity.etl.FaultWeek;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class FaultExtractDataService implements IFaultExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IFaultEtlService faultEtlService;
	
	@Override
	public void extractStatusByWeek(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(date);
		SQLQuery query = dao.getSQLQuery(getFaultStatusSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				IFaultWeek day = new FaultWeek();
				day.setDate(DateUtils.getWeek(date));
				day.setOpenCount(Long.parseLong(each[0].toString()));
				day.setCloseCount(Long.parseLong(each[1].toString()));
				faultEtlService.saveByFaultWeek(day);
			}
		}
	}
	
	private String getFaultStatusSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(case WHEN cf.FAULT_STATUS = 'OPEN' then 1 ELSE 0 END), ");
		sql.append("sum(case WHEN cf.FAULT_STATUS = 'CLOSED' then 1 ELSE 0 END) ");
		sql.append("FROM CASE_FAULT cf ");
		sql.append("where cf.FAULT_DATE >= ? and cf.FAULT_DATE <= ? ");
		return sql.toString();
	}

	@Override
	public void extractStatusByMonth(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofMonth(date);
		SQLQuery query = dao.getSQLQuery(getFaultStatusSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				long lastMonth = Long.parseLong(DateUtils.getYM(date));
				IFaultMonth faultMonth = exsitFaultMonth(lastMonth);
				if(null==faultMonth){
					faultMonth = new FaultMonth();
				}
				faultMonth.setDate(lastMonth);
				faultMonth.setOpenCount(Long.parseLong(each[0].toString()));
				faultMonth.setCloseCount(Long.parseLong(each[1].toString()));
				faultEtlService.saveByFaultMonth(faultMonth);
			}
		}
	}
	
	/**
	 * @param month
	 * @return
	 */
	public IFaultMonth exsitFaultMonth(long month){
		StringBuilder sb = new StringBuilder();
		sb.append(" from ").append(FaultMonth.class.getSimpleName()).append( " faultMonth where faultMonth.date = ?");
		List<IFaultMonth> list = dao.findByHQL(sb.toString(), month);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void extractFaultClassifyByWeek(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(date);
		SQLQuery query = dao.getSQLQuery(getFaultClassifySql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				IFaultClassifyWeek day = new FaultClassifyWeek();
				day.setDate(DateUtils.getWeek(date));
				day.setClassifyId(each[0].toString());
				day.setClassifyName(each[1].toString());
				day.setCount(Long.parseLong(each[2].toString()));
				faultEtlService.saveByFaultClassifyWeek(day);
			}
		}
	}
	
	private String getFaultClassifySql(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cf.CLASSIFY_ID,cfc.CLASSIFY_NAME,count(cf.ID) ");
		sql.append("FROM CASE_FAULT cf,CASE_FAULT_CLASSIFY cfc ");
		sql.append("where cf.CLASSIFY_ID = cfc.ID and cf.FAULT_DATE >= ? and cf.FAULT_DATE <= ? ");
		sql.append("group by cf.CLASSIFY_ID");
		return sql.toString();
	}

	@Override
	public void extractFaultClassifyByMonth(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofMonth(date);
		SQLQuery query = dao.getSQLQuery(getFaultClassifySql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				IFaultClassifyMonth day = new FaultClassifyMonth();
				day.setDate(Long.parseLong(DateUtils.getYM(date)));
				day.setClassifyId(each[0].toString());
				day.setClassifyName(each[1].toString());
				day.setCount(Long.parseLong(each[2].toString()));
				faultEtlService.saveByFaultClassifyMonth(day);
			}
		}
	}

	@Override
	public void extractDurationByWeek(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(date);
		SQLQuery query = dao.getSQLQuery(getDurationSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				for(int i = 1 ;i < 7;i++){
					IFaultDurationWeek day = new FaultDurationWeek();
					day.setDate(DateUtils.getWeek(date));
					day.setDuration(i);
					day.setCount(Long.parseLong(each[i-1].toString()));
					faultEtlService.saveByFaultDurationWeek(day);
				}
			}
		}
	}
	
	@Override
	public void extractDurationByMonth(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofMonth(date);
		SQLQuery query = dao.getSQLQuery(getDurationSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){//当没有数据时，会出现一个空行
				for(int i = 1 ;i < 7;i++){
					IFaultDurationMonth day = new FaultDurationMonth();
					day.setDate(Long.parseLong(DateUtils.getYM(date)));
					day.setDuration(i);
					day.setCount(Long.parseLong(each[i-1].toString()));
					faultEtlService.saveByFaultDurationMonth(day);
				}
			}
		}
	}
	
	private String getDurationSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(case WHEN cf.DURATION <= 0.5 then 1 ELSE 0 END),");
		sql.append("sum(case WHEN cf.DURATION <= 2 and cf.DURATION > 0.5 then 1 ELSE 0 END),");
		sql.append("sum(case WHEN cf.DURATION <= 4 and cf.DURATION > 2 then 1 ELSE 0 END),");
		sql.append("sum(case WHEN cf.DURATION <= 24 and cf.DURATION > 4 then 1 ELSE 0 END),");
		sql.append("sum(case WHEN cf.DURATION <= 72 and cf.DURATION > 24 then 1 ELSE 0 END),");
		sql.append("sum(case WHEN cf.DURATION > 72 then 1 ELSE 0 END)");
		sql.append("FROM CASE_FAULT cf ");
		sql.append("where cf.FAULT_DATE >= ? and cf.FAULT_DATE <= ? ");
		sql.append("and cf.FAULT_STATUS = 'CLOSED' AND cf.DURATION IS NOT NULL");
		return sql.toString();
	}

}
