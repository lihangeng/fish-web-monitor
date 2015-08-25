package com.yihuacomputer.fish.api.atmlog;

/**
 * ATM APP日志备份结果
 * @author YiHua
 *
 */
public enum BackupResult {
	SUCCESS,//成功
	UNDO,//任务未开始
	ERROR_CONNECT,//连接ATM设备失败
	ERROR_NOLOG,//无当日ATM日志
	ERROR;//未知原因失败
}
