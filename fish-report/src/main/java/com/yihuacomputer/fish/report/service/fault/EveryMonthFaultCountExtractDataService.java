package com.yihuacomputer.fish.report.service.fault;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountExtractDataService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;

@Service
@Transactional
public class EveryMonthFaultCountExtractDataService implements IEveryMonthFaultCountExtractDataService {

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IEveryMonthFaultCountService everyMonthFaultCountService;
	@Override
	public void extractMonthFault(String date) {

		StringBuffer sql = new StringBuffer();
		long date1 = Long.parseLong(date);
		long dateBegin = date1*100;
		long dateEnd = (date1+1)*100;
		sql.append("select DEV_TYPE.NAME typeName,DEV_MOD,CLASSIFY_ID,FAULT_DATE,count(CASE_FAULT.TERMINAL_ID),DEV_VENDOR.NAME vendorName,DEV_VENDOR.ID vendorId,DEV_TYPE.ID typeId ");
		sql.append("from CASE_FAULT,DEV_INFO,DEV_TYPE,DEV_VENDOR ").append("where FAULT_DATE > ").append(dateBegin).append(" and FAULT_DATE<").append(dateEnd)
		.append(" and CLASSIFY_ID='F_MOD' ");
		sql.append(" and CASE_FAULT.TERMINAL_ID=DEV_INFO.TERMINAL_ID and DEV_INFO.DEV_TYPE_ID=DEV_TYPE.ID AND DEV_TYPE.DEV_VENDOR_ID=DEV_VENDOR.ID ")
		.append( "GROUP BY DEV_MOD,CLASSIFY_ID ,DEV_TYPE.NAME,DEV_VENDOR.NAME,DEV_VENDOR.ID,DEV_TYPE.ID");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> info = query.list();
		for (Object object : info) {
			Object[] obj = (Object[]) object;
			IEveryMonthFaultCount everyMonthFaultCount = everyMonthFaultCountService.make();
			everyMonthFaultCount.setDevType(obj[0]==null?"":obj[0].toString());
			everyMonthFaultCount.setDevMod(obj[1]==null?"":obj[1].toString());
			everyMonthFaultCount.setClassifyId(obj[2]==null?"":obj[2].toString());
			everyMonthFaultCount.setFaultDate(date1);
			everyMonthFaultCount.setFaultCount(Long.valueOf(obj[4]==null?"":obj[4].toString()));
			everyMonthFaultCount.setVendorName(obj[5]==null?"":obj[5].toString());	
			everyMonthFaultCount.setVendorId(obj[6] == null ? 0l : Long.parseLong(String.valueOf(obj[6])));
			everyMonthFaultCount.setDevTypeId(obj[7] == null ? 0l : Long.parseLong(String.valueOf(obj[7])));
			everyMonthFaultCountService.add(everyMonthFaultCount);
		}
	}

}
