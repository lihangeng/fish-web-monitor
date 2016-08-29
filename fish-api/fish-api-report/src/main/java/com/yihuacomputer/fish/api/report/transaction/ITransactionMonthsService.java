package com.yihuacomputer.fish.api.report.transaction;

import java.util.List;

public interface ITransactionMonthsService {

	public ITransactionMonths make();

	public void save(ITransactionMonths transactionMonths);

	public List<ITransactionMonths> list();

	/**
	 * @param date yyyyMM
	 */
	public void extractDate(String date);

}
