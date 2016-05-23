package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.ITransaction;

public interface ITransactionDaysService {

	public ITransactionDays make();

	public void save(ITransactionDays transactionDay);

	public List<ITransactionDays> list();

	public List<ITransaction> list2();

	/**
	 * @param date yyyyMMdd
	 */
	public void extractDate(String date);



}
