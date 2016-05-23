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

		return null;
	}


	@Override
	public void extractDate(String date){

		StringBuffer sql=new StringBuffer();
		sql.append("select terminal_id,trans_date,count(*) from atmc_transaction ");
		sql.append("where trans_date=");
		sql.append(date);
		sql.append(" group by terminal_id");
		SQLQuery query =dao.getSQLQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object> infos = query.list();
		for(Object object:infos){
			Object[] objs = (Object[]) object;
			TransactionDays td=new TransactionDays();
			td.setTerminalId(objs[0]==null?"":String.valueOf(objs[0]));
			td.setTransDate(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1])));
			td.setTransCount(Long.parseLong(objs[2]==null?"0":String.valueOf(objs[2])));
			save(td);

		}



	}

}
