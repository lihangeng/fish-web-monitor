package com.yihuacomputer.fish.api.batch.base;

import java.util.List;

public interface IETLjobMonthService {
	public void extractDate(String date);

	public List<String> dateList(long oldstr,long xinstr);
}