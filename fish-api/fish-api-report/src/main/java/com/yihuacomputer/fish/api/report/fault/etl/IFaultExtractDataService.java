package com.yihuacomputer.fish.api.report.fault.etl;

import java.util.Date;

public interface IFaultExtractDataService {

	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractStatusByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractStatusByMonth(Date date);
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractFaultClassifyByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractFaultClassifyByMonth(Date date);
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractDurationByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractDurationByMonth(Date date);
	
}
