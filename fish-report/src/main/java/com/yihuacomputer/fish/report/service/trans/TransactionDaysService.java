package com.yihuacomputer.fish.report.service.trans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.report.trans.ITransactionDays;
import com.yihuacomputer.fish.api.report.trans.ITransactionDaysService;
import com.yihuacomputer.fish.report.entity.TransactionDays;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class TransactionDaysService implements ITransactionDaysService {

	@Autowired
	private IGenericDao dao;

	@Override
	public ITransactionDays make() {
		return new TransactionDays();
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"devTypeId","transCode","transDate"})
	@Override
	public void save(ITransactionDays transactionDay) {
		dao.save(transactionDay);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ITransaction> list2() {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ITransactionDays> list() {
		return dao.loadAll(ITransactionDays.class);
	}

}
