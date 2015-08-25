package com.yihuacomputer.fish.atmlog.job;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * 备份作业队列
 * @author YiHua
 *
 */
public class BackupQueue {
	
	public static final int backupQueueLength = 30 ;

	private static BlockingQueue<BackupRule> backupQueue = new ArrayBlockingQueue<BackupRule>(BackupQueue.backupQueueLength);

	public BackupQueue(){
		
	}
	/**
	 * 获取任务执行队列的使用权
	 * */
	public static BlockingQueue<BackupRule> getBackupQueue(){
		return BackupQueue.backupQueue;
	}
	
	public static int getBackupQueueLength(){
		return BackupQueue.backupQueueLength;
	}
	
}
