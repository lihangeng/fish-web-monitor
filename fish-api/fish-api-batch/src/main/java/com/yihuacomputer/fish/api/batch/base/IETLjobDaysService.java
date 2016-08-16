package com.yihuacomputer.fish.api.batch.base;

import java.util.List;

public interface IETLjobDaysService {
	public String extractDate(String date);

	public List<String> dateList(long oldstr,long xinstr);
}
