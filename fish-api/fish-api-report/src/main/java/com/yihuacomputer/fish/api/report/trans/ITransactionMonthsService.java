package com.yihuacomputer.fish.api.report.trans;

import java.util.List;

/**
 * @author YiHua
 *
 */
public interface ITransactionMonthsService {

	/**
	 * @return
	 */
	public ITransactionMonths make();

	/**
	 * @param transactionMonths
	 */
	public void save(ITransactionMonths transactionMonths);

	/**
	 * @return
	 */
	public List<ITransactionMonths> list();

	/**
	 * @param date yyyyMM
	 */
	public void extractDate(String date);

}
