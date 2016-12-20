package com.yihuacomputer.fish.atmlog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupFileCfg;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

public class BackupThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(BackupThread.class);

	private IAtmLogService logService;
	
	private IBackupFileCfg backupFileCfg;

	private BackupThreadPool pool;
	
	public BackupThread(IAtmLogService logService,BackupThreadPool pool,IBackupFileCfg backupFileCfg){
		this.logService = logService;
		this.pool = pool;
		this.backupFileCfg = backupFileCfg;
	}	

	@Override
	public void run() {		

		/**
		 * 读取队列获取备份任务并且执行
		 */

		while (true) {

			try {
				BackupRule rule = (BackupRule) BackupQueue.getBackupQueue().take();
				BackupRuner runner = new BackupRuner(logService,backupFileCfg);
				runner.setBackupRule(rule);
				pool.execute(runner);
			} catch (Exception e) {
				logger.error(String.format("Backup file Thread error![%s]",e));
				break;
			}
		}
	}

}
