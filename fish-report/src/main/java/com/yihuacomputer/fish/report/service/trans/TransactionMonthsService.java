package com.yihuacomputer.fish.report.service.trans;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonths;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonthsService;
import com.yihuacomputer.fish.report.entity.TransactionMonths;

@Service
@Transactional
public class TransactionMonthsService implements ITransactionMonthsService {

	@Autowired
	private IGenericDao dao;

	@Override
	public ITransactionMonths make() {
		return new TransactionMonths();
	}

	@Override
	public void save(ITransactionMonths transactionMonths) {
		dao.save(transactionMonths);

	}

	@Override
	public List<ITransactionMonths> list() {
		return dao.loadAll(ITransactionMonths.class);
	}

	@Override
	public void extractDate(String date) {
		long date1 = Long.parseLong(date);
		long dateBegin = date1 * 100;
		long dateEnd = (date1 + 1) * 100;
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(TRANS_AMT),sum(TRANS_COUNT),TRANS_CODE,CARD_TYPE,DEV_TYPE,VENDOR_NAME,DEV_TYPE_ID,VENDOR_ID from ATMC_TRANSACTION_DAYS ");
		sql.append("where TRANS_DATE>");
		sql.append(dateBegin);
		sql.append(" and TRANS_DATE<");
		sql.append(dateEnd);
		sql.append(" group by CARD_TYPE,TRANS_CODE,DEV_TYPE,VENDOR_NAME,DEV_TYPE_ID,VENDOR_ID");
		SQLQuery query = dao.getSQLQuery(sql.toString());

		try {
			@SuppressWarnings("unchecked")
			List<Object> infos = query.list();
			for (Object object : infos) {
				Object[] objs = (Object[]) object;
				ITransactionMonths tm = make();
				tm.setTransAmt(Long.parseLong(objs[0] == null ? "0" : String.valueOf(objs[0])));
				tm.setTransCount(Long.parseLong(objs[1] == null ? "0" : String.valueOf(objs[1])));
				tm.setTransCode(objs[2] == null ? "" : String.valueOf(objs[2]));
				tm.setCardType(objs[3] == null ? "" : String.valueOf(objs[3]));
				tm.setDevType(objs[4] == null ? "" : String.valueOf(objs[4]));
				tm.setVendorName(objs[5] == null ? "" : String.valueOf(objs[5]));
				tm.setDevTypeId(objs[6] == null ? 0l : Long.parseLong(String.valueOf(objs[6])));
				tm.setVendorId(objs[7] == null ? 0l : Long.parseLong(String.valueOf(objs[7])));
				tm.setTransDate(date1);
				save(tm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
