package com.yihuacomputer.fish.api.atmlog;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDayBackupService {

	/**
	 * 保存每日日志备份任务结果
	 * @param dayBackupLog
	 */
	public void save(IDayBackupLog dayBackupLog);

	/**
	 * 获取指定日期下日志备份结果
	 * @param date
	 * @return
	 */
	public IDayBackupLog get(String date);
	
	
	/**
	 * 创建实体
	 * @return
	 */
	public IDayBackupLog make();
	
	/**
	 * 更新备份任务信息
	 * @param dayBackupLog
	 */
	public void update(IDayBackupLog dayBackupLog);
	
	public IPageResult<IDayBackupLog> pageList(int start , int limit , IFilter filter);

}
