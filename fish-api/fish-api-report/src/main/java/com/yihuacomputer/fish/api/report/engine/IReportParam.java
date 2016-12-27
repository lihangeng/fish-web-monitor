package com.yihuacomputer.fish.api.report.engine;

import java.util.List;
import java.util.Map;

/**
 * @author YiHua
 *
 */
public interface IReportParam {

	/**
	 * 是否输出PDF
	 * 
	 * @return
	 */
	public boolean isPdf();

	/**
	 * 是否输出HTML
	 * 
	 * @return
	 */
	public boolean isHtml();

	/**
	 * 是否输出XLS
	 * 
	 * @return
	 */
	public boolean isXls();

	/**
	 * 生成xls报表工作薄的名称
	 * 
	 * @return
	 */
	public String[] getSheetNames();

	/**
	 * 报表参数
	 * 
	 * @return
	 */
	public Map<String, Object> getParameters();

	/**
	 * 报表模板
	 * 
	 * @return
	 */
	public String getReportModule();

	/**
	 * 数据结合
	 * 
	 * @return
	 */
	public List<?> getDataList();

	/**
	 * 生成报表存放路径
	 * 
	 * @return
	 */
	public String getReportFilepath();
}
