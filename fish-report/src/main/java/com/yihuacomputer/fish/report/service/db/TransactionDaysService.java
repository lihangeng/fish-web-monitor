package com.yihuacomputer.fish.report.service.db;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.report.base.ITransactionDays;
import com.yihuacomputer.fish.api.report.base.ITransactionDaysService;
import com.yihuacomputer.fish.report.entity.TransactionDays;

@Service
@Transactional
public class TransactionDaysService implements ITransactionDaysService{


	 @Autowired
	 private IGenericDao dao;

	 @Override
	public ITransactionDays make() {
		return new TransactionDays();
	}

	@Override
	public void save(ITransactionDays transactionDay) {
		dao.save(transactionDay);
	}


	@Override
	@Transactional(readOnly = true)
	public List<ITransaction> list2(){
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ITransactionDays> list() {
		return dao.loadAll(ITransactionDays.class);
	}


	@Override
	public void extractDate(String date){
		long datel = Long.parseLong(date);
		StringBuffer sql=new StringBuffer();
		sql.append("select sum(AMT),count(ATMC_TRANSACTION.ID),TRANS_CODE,CARD_TYPE, ").
		append("DEV_TYPE.NAME typeName,DEV_VENDOR.NAME vendorName FROM ATMC_TRANSACTION,DEV_INFO,DEV_TYPE,DEV_VENDOR ").
		append("WHERE ATMC_TRANSACTION.TRANS_DATE=").append(datel);
		sql.append(" and ATMC_TRANSACTION.TERMINAL_ID=DEV_INFO.TERMINAL_ID and DEV_INFO.DEV_TYPE_ID=DEV_TYPE.ID AND DEV_TYPE.DEV_VENDOR_ID=DEV_VENDOR.ID GROUP BY TRANS_CODE,CARD_TYPE,DEV_TYPE.NAME,DEV_VENDOR.NAME,CARD_TYPE");
		SQLQuery query =dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> infos = query.list();
		for(Object object:infos){
			Object[] objs = (Object[]) object;
			TransactionDays td=new TransactionDays();
			td.setTransAmt(Long.parseLong(objs[0]==null?"0":String.valueOf(objs[0]).split("\\.")[0]));
			td.setTransCount(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1]).split("\\.")[0]));
			td.setTransCode(objs[2]==null?"":String.valueOf(objs[2]));
			td.setCardType(objs[3]==null?"":String.valueOf(objs[3]));
			td.setDevType(objs[4]==null?"":String.valueOf(objs[4]));
			td.setVendorName(objs[5]==null?"":String.valueOf(objs[5]));
			td.setTransDate(datel);
			save(td);
		}
	}

}
