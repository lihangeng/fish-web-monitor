package com.yihuacomputer.fish.report.service.trans;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.trans.ITransactionDays;
import com.yihuacomputer.fish.api.report.trans.ITransactionDaysExtractDataService;
import com.yihuacomputer.fish.api.report.trans.ITransactionDaysService;

@Service
@Transactional
public class TransactionDaysExtractDataService implements ITransactionDaysExtractDataService {
	
	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private ITransactionDaysService transactionDaysService;

	@Override
	public void extractDate(String date) {
		long datel = Long.parseLong(date);
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(AMT),count(ATMC_TRANSACTION.ID),TRANS_CODE,CARD_TYPE, ")
		.append("DEV_TYPE.NAME typeName,DEV_VENDOR.NAME vendorName,DEV_TYPE.ID devTypeId,DEV_VENDOR.ID devVendorId FROM ATMC_TRANSACTION,DEV_INFO,DEV_TYPE,DEV_VENDOR ").
		append("WHERE ATMC_TRANSACTION.TRANS_DATE=").append(datel);
		sql.append(" and ATMC_TRANSACTION.TERMINAL_ID=DEV_INFO.TERMINAL_ID and DEV_INFO.DEV_TYPE_ID=DEV_TYPE.ID AND DEV_TYPE.DEV_VENDOR_ID=DEV_VENDOR.ID GROUP BY TRANS_CODE,CARD_TYPE,DEV_TYPE.NAME,DEV_VENDOR.NAME,DEV_TYPE.ID,DEV_VENDOR.ID");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> infos = query.list();
		for (Object object : infos) {
			Object[] objs = (Object[]) object;
			ITransactionDays td = transactionDaysService.make();
			td.setTransAmt(Long.parseLong(objs[0] == null ? "0" : String.valueOf(objs[0]).split("\\.")[0]));
			td.setTransCount(Long.parseLong(objs[1] == null ? "0" : String.valueOf(objs[1]).split("\\.")[0]));
			td.setTransCode(objs[2] == null ? "" : String.valueOf(objs[2]));
			td.setCardType(objs[3] == null ? "" : String.valueOf(objs[3]));
			td.setDevType(objs[4] == null ? "" : String.valueOf(objs[4]));
			td.setVendorName(objs[5] == null ? "" : String.valueOf(objs[5]));
			td.setDevTypeId(objs[6] == null ? 0l : Long.parseLong(String.valueOf(objs[6])));
			td.setVendorId(objs[7] == null ? 0l : Long.parseLong(String.valueOf(objs[7])));
			td.setTransDate(datel);
			transactionDaysService.save(td);
		}
	}

}
