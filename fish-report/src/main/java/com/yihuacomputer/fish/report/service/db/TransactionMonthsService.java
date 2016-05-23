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
		return null;
	}

	@Override
	public void save(ITransactionMonths transactionMonths) {
		 dao.save(transactionMonths);

	}

	@Override
	public List<ITransactionMonths> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void extractDate(String date){

		StringBuffer sql=new StringBuffer();
		String currDate = date;
		String startDate = currDate + "01";
		String endDate = currDate + "31";
		sql.append("select terminal_id,trans_date,count(*) from atmc_transaction_days ");
		sql.append("where trans_date between '");
		sql.append(startDate);
		sql.append("' and '");
		sql.append(endDate);
		sql.append("' group by terminal_id");
		SQLQuery query =dao.getSQLQuery(sql.toString());

		try{
		@SuppressWarnings("unchecked")
		List<Object> infos = query.list();
		for(Object object:infos){
			Object[] objs = (Object[]) object;
			TransactionMonths td=new TransactionMonths();
			td.setTerminalId(objs[0]==null?"":String.valueOf(objs[0]));
			td.setTransDate(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1])));
			td.setTransCount(Long.parseLong(objs[2]==null?"0":String.valueOf(objs[2])));
			save(td);
		}
		}catch(Exception e){
			e.printStackTrace();
		}






	}



}
