package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 按照网点的开机率数据
 * @author xuxiang
 *
 */
public interface IOrgOpenRateMonth extends IOpenRateColumn{
	/**
	 * 获得统计的日期,格式为yyyyMM
	 * 如201611
	 * @return
	 */
	long getDate();
	void setDate(long date);
	
	/**
	 * 机构编号
	 * @return
	 */
	String getOrgCode();
	void setOrgCode(String orgCode);
	
	/**
	 * 机构名称
	 * @return
	 */
	String getOrgName();
	void setOrgName(String orgName);
	
}
