package com.yihuacomputer.fish.report.service.db;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;
import com.yihuacomputer.fish.api.report.base.ITransactionMonthsService;
import com.yihuacomputer.fish.report.entity.TransactionMonths;

@Service
@Transactional
public class TransactionMonthsService implements ITransactionMonthsService{


	@Autowired
	 private IGenericDao dao;

	@Override
	public ITransactionMonths make() {
		// TODO Auto-generated method stub
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
	public void extractDate(String date){
		long date1 = Long.parseLong(date);
		long dateBegin = date1*100;
		long dateEnd = (date1+1)*100;
		StringBuffer sql=new StringBuffer();
		sql.append("select sum(TRANS_AMT),sum(TRANS_COUNT),TRANS_CODE,CARD_TYPE,DEV_TYPE,VENDOR_NAME from ATMC_TRANSACTION_DAYS ");
		sql.append("where trans_date>");
		sql.append(dateBegin);
		sql.append(" and trans_date<");
		sql.append(dateEnd);
		sql.append(" group by CARD_TYPE,TRANS_CODE,DEV_TYPE,VENDOR_NAME");
		SQLQuery query =dao.getSQLQuery(sql.toString());

		try{
		@SuppressWarnings("unchecked")
		List<Object> infos = query.list();
		for(Object object:infos){
			Object[] objs = (Object[]) object;
			TransactionMonths tm=new TransactionMonths();
			tm.setTransAmt(Long.parseLong(objs[0]==null?"0":String.valueOf(objs[0])));
			tm.setTransCount(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1])));
			tm.setTransCode(objs[2]==null?"":String.valueOf(objs[2]));
			tm.setCardType(objs[3]==null?"":String.valueOf(objs[3]));
			tm.setDevType(objs[4]==null?"":String.valueOf(objs[4]));
			tm.setVendorName(objs[5]==null?"":String.valueOf(objs[5]));
			tm.setTransDate(date1);
			save(tm);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
