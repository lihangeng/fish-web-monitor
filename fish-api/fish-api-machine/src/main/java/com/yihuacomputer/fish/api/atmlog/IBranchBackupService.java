package com.yihuacomputer.fish.api.atmlog;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IBranchBackupService {

	public IBranchBackupInfo make();
	
	public void save(IBranchBackupInfo branchBackupInfo);
	
	public void update(IBranchBackupInfo branchBackupInfo);
	
	/**
	 * 根据机构编号和日期获取当天备份记录
	 * @param date
	 * @param orgId
	 * @return
	 */
	public IBranchBackupInfo getDayBranchBackupInfo(String date,String orgId);
	
	/**
	 * 根据条件取备份记录
	 * @param date
	 * @param orgId
	 * @return
	 */
	public IPageResult<IBranchBackupInfo> getDayBranchBackupInfos(int offset,int limit,IFilter filter);
	

	public List<IBranchBackupInfo> getDayBranchBackupFailerInfos(IFilter filter);
}
