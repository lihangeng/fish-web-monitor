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
public class EveryMonthFaultCountService implements IEveryMonthFaultCountService {

	@Autowired
	private IGenericDao dao;

	@Override
	public void add(IEveryMonthFaultCount everyMonthFaultCount) {
		dao.save(everyMonthFaultCount);
	}
	@Override
	public IEveryMonthFaultCount make() {
		return new EveryMonthFaultCount();
	}

	@Override
	public void extractMonthFault(String date) {

		StringBuffer sql = new StringBuffer();
		long date1 = Long.parseLong(date);
		long dateBegin = date1*100;
		long dateEnd = (date1+1)*100;
		sql.append("select DEV_TYPE.NAME typeName,DEV_MOD,CLASSIFY_ID,FAULT_DATE,count(CASE_FAULT.TERMINAL_ID),DEV_VENDOR.NAME vendorName  ");
		sql.append("from CASE_FAULT,DEV_INFO,DEV_TYPE,DEV_VENDOR ").append("where FAULT_DATE > ").append(dateBegin).append(" and FAULT_DATE<").append(dateEnd)
		.append(" and CLASSIFY_ID='F_MOD' ");
		sql.append(" and CASE_FAULT.TERMINAL_ID=DEV_INFO.TERMINAL_ID and DEV_INFO.DEV_TYPE_ID=DEV_TYPE.ID AND DEV_TYPE.DEV_VENDOR_ID=DEV_VENDOR.ID ")
		.append( "GROUP BY DEV_MOD,CLASSIFY_ID ,DEV_TYPE.NAME,DEV_VENDOR.NAME");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> info = query.list();
		for (Object object : info) {
			Object[] obj = (Object[]) object;
			IEveryMonthFaultCount everyMonthFaultCount = make();
			everyMonthFaultCount.setDevType(obj[0]==null?"":obj[0].toString());
			everyMonthFaultCount.setDevMod(obj[1]==null?"":obj[1].toString());
			everyMonthFaultCount.setClassifyId(obj[2]==null?"":obj[2].toString());
			everyMonthFaultCount.setFaultDate(date1);
			everyMonthFaultCount.setFaultCount(Long.valueOf(obj[4]==null?"":obj[4].toString()));
			everyMonthFaultCount.setVendorName(obj[5]==null?"":obj[5].toString());
			add(everyMonthFaultCount);
		}
	}

}
