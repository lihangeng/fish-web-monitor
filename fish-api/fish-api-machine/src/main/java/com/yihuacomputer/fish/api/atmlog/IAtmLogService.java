package com.yihuacomputer.fish.api.atmlog;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * ATM 日志备份管理
 * @author YiHua
 *
 */
public interface IAtmLogService {

	/**
	 * 创建日志记录实体
	 * @return
	 */
	public IAtmLog make();
	
	/**
	 * 保存日志信息
	 * @param atmLog
	 */
	public void saveAtmLog(IAtmLog atmLog);
	
	/**
	 * 更新日志记录
	 * @param atmLog
	 */
	public void updateAtmLog(IAtmLog atmLog);
	
	/**
	 * 获取设备指定日期的日志记录
	 * @param terminalId
	 * @param date
	 * @return
	 */
	public IAtmLog getAtmLog(String terminalId,String date);
	
	/**
	 * 获取备份失败的记录信息
	 * @return
	 */
	public List<IBackupRule> loadErrorLogs();
	
	public IPageResult<IAtmLog> pageList(int start , int limit , IFilter filter);
	
	/**
	 * 获取指定日期失败的日志记录
	 * @param date
	 * @return
	 */
	public List<Object> loadDayErrorLogs(String date);
	
	/**
	 * 获取备份失败的记录，并分页
	 * @param start
	 * @param limit
	 * @param Date
	 * @param orgId
	 * @return
	 */
	public IPageResult<IAtmLog> pageErrorList(int start, int limit, String date, long orgId);
	
	/**
	 * 获取备份成功的记录，并分页
	 * @param start
	 * @param limit
	 * @param date
	 * @param orgId
	 * @return
	 */
	public IPageResult<IAtmLog> pageSuccessList(int start, int limit, String date, long orgId);
	
	/**
	 * 获取指定机构与日期成功的日志记录
	 * @param date
	 * @return
	 */
	public List<IAtmLog> loadDaySuccessLogs(String date,long orgId);
	
	/**
	 * 获取指定机构与日期失败的日志记录
	 * @param date
	 * @param orgId
	 * @return
	 */
	public List<IAtmLog> loadDayErrorLogs(String date, long orgId);
	
	/**
	 * 从日志备份信息表中，获取当日备份成功的冠字号设备信息
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<IAtmLog> pageImportDevice(int offset,int limit);
}
