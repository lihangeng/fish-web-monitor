package com.yihuacomputer.fish.api.report.trans;

import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.ITransaction;

/**
 * @author YiHua
 *
 */
public interface ITransactionDaysService {

	/**
	 * @return
	 */
	public ITransactionDays make();

	/**
	 * @param transactionDay
	 */
	public void save(ITransactionDays transactionDay);

	/**
	 * @return
	 */
	public List<ITransactionDays> list();

	/**
	 * @return
	 */
	public List<ITransaction> list2();

}
