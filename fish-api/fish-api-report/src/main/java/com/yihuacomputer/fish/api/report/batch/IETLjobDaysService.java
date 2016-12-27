package com.yihuacomputer.fish.api.report.batch;

import java.util.List;

/**
 * @author YiHua
 *
 */
public interface IETLjobDaysService {
	/**
	 * @param date
	 * @return
	 */
	public String extractDate(String date);

	/**
	 * @param oldstr
	 * @param xinstr
	 * @return
	 */
	public List<String> dateList(long oldstr,long xinstr);
}
