package com.yihuacomputer.fish.atmlog.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupRule;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * 重做失败工作
 * @author YiHua
 *
 */
public class RedoJob implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(RedoJob.class);

	private IAtmLogService atmLogService;

	public RedoJob(IAtmLogService atmLogService){
		this.atmLogService = atmLogService;
	}
	
	public void run() {
		while(true){
			List<IBackupRule> atmLogs = atmLogService.loadErrorLogs();
			try {
				for (IBackupRule rule : atmLogs) {
					BackupQueue.getBackupQueue().put((BackupRule) rule);
				}
			} catch (InterruptedException e) {
				logger.error(String.format("redo daybackup log error！[%s]",e));
				continue;
			}
			try {
				Thread.sleep(2*60*60*1000);
			} catch (InterruptedException e) {
				logger.error(String.format("redo daybackup sleep error！[%s]",e));
				break;
			}
		}	
	}	
}
