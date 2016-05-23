package com.yihuacomputer.fish.api.report.base;

import java.util.List;

public interface ITransactionMonthsService {

	public ITransactionMonths make();

	public void save(ITransactionMonths transactionMonths);

	public List<ITransactionMonths> list();

	public void extractDate(String date);

}
