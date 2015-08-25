package com.yihuacomputer.fish.api.atmlog;

public interface IBackupFileCfg {
	
	/**
	 * 服务端日志文件存储路径
	 * @param backupRule
	 * @return
	 */
	public String getServerFilePath(IBackupRule backupRule);
	
	/**
	 * 服务端日志文件存放存放名称
	 * @param backupRule
	 * @return
	 */
	public String getServerFileName(IBackupRule backupRule);
	
	/**
	 * 客户端日志文件存放路径
	 * @param backupRule
	 * @return
	 */
	public String getAtmFilePath(IBackupRule backupRule);
	
	/**
	 * 客户端日志文件存放名称
	 * @param backupRule
	 * @return
	 */
	public String getAtmFileName(IBackupRule backupRule);
}
