package com.yihuacomputer.fish.atmlog.job;

import java.lang.Thread.State;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupFileCfg;
import com.yihuacomputer.fish.api.atmlog.IBackupManagerStatus;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * Atm App日志备份管理器
 * @author YiHua
 *
 */
@Service
public class BackupManager implements IBackupManagerStatus{

	@Autowired
	private IAtmLogService atmLogService;
	
	@Autowired
	private IBackupFileCfg backupFileCfg;
	
	private Thread redoJob;
	private Thread backupThread;
	
	private BackupThreadPool pool;
	
	/**
	 * 初始化工作
	 */	
	@PostConstruct
	public void init(){	
		
		pool = new BackupThreadPool();
		backupThread = new Thread(new BackupThread(atmLogService,pool,backupFileCfg));
		backupThread.setName("Backup_Job");
		backupThread.start();
		
		redoJob = new Thread(new RedoJob(atmLogService));
		redoJob.setName("ReBackup_job");
		redoJob.start();
	}
	
	/**
	 * 关闭线程池，否则WEB器在停止应用时无法结束
	 */
	@PreDestroy
	public void close(){
		if(backupThread!=null){
			backupThread.interrupt();
		}
		if(this.redoJob!=null){
			this.redoJob.interrupt();			
		}
		if(pool!=null){
			pool.close();
		}
	}
	
	/**
	 * 获取备份特权
	 */
	public void getBackupPrivilege(){
		if(this.redoJob!=null){
			redoJob.setPriority(Thread.MIN_PRIORITY);
		}		
	}
	
	/**
	 * 每日备份
	 * @param atmLogs
	 * @throws Exception
	 */
	public void dayBackup(List<BackupRule> atmLogs) throws Exception{
		for(BackupRule rule:atmLogs){
			BackupQueue.getBackupQueue().put(rule);
		}

		if(redoJob!=null){
			redoJob.setPriority(Thread.NORM_PRIORITY);
		}		
	}

	@Override
	public State getDaybackThreadState() {
		return backupThread.getState();
	}

	@Override
	public State getRedoBackTreadState() {
		return redoJob.getState();
	}

	@Override
	public int getActiveRuners() {
		return pool.getActvieCount();
	}	
}
