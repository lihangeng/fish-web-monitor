package com.yihuacomputer.fish.api.report.batch;

import java.util.List;

/**
 * @author YiHua
 *
 */
public interface IETLjobMonthService {
	/**
	 * @param date
	 */
	public void extractDate(String date);

	/**
	 * @param oldstr
	 * @param xinstr
	 * @return
	 */
	public List<String> dateList(long oldstr,long xinstr);
}
