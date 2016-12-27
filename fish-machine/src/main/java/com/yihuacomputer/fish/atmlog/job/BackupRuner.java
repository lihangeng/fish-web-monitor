package com.yihuacomputer.fish.atmlog.job;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpFileRet;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupFileCfg;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * 备份执行器，执行当备份任务
 * @author YiHua
 *
 */
public class BackupRuner implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(BackupRuner.class);

	private IAtmLogService logService;

	private IBackupFileCfg backupFileCfg;

	private BackupRule rule;

	/**
	 * @param logService
	 * @param backupFileCfg
	 */
	public BackupRuner(IAtmLogService logService,IBackupFileCfg backupFileCfg){
		this.logService = logService;
		this.backupFileCfg = backupFileCfg;
	}

	public void setBackupRule(BackupRule rule){
		this.rule = rule;
	}

	@Override
	public void run() {

		HttpFileCfg fileCfg = new HttpFileCfg();
		fileCfg.setIpAdd(this.rule.getTerminalIp());
		fileCfg.setPort(MonitorCfg.getRemotePort());
		fileCfg.setCompress(true);
		fileCfg.setRetry(true);
		fileCfg.setRequestPath(backupFileCfg.getAtmFilePath(rule));
		fileCfg.setLocalPath(backupFileCfg.getServerFilePath(rule));
		fileCfg.setRequestName(backupFileCfg.getAtmFileName(rule));
		fileCfg.setLocalName(backupFileCfg.getServerFileName(rule));
		BackupResult ret = BackupResult.ERROR;

		try{
			ret = this.getBackupResult(HttpFileClient.downloadFile(fileCfg));
		}catch(Exception e){
			logger.error(String.format("[%s]", e));
			ret =  BackupResult.ERROR;
		}

		IAtmLog log = logService.getAtmLog(rule.getTerminalId(), rule.getBackupDate());

		log.setDoTimes(log.getDoTimes()+1);
		log.setLastDoDate(DateUtils.getTimestamp(new Date()));

		if(ret.equals(BackupResult.SUCCESS)){
			log.setLogSize(getLogSize(fileCfg));
		}
		log.setBackupResult(ret);
		logService.updateAtmLog(log);
	}

	/**
	 * 获取日志文件大小
	 * @return
	 */
	private long getLogSize(HttpFileCfg cfg){
		File logFile = new File(cfg.getLocalPath()+FishCfg.FILESEP+cfg.getLocalName());

		if(logFile.exists()){
			return logFile.length();
		}else{
			return 0;
		}
	}
	/**
	 * 判断获取文件返回码
	 * @param fileRet
	 * @return
	 */
	private BackupResult getBackupResult(HttpFileRet fileRet){

		switch(fileRet){
			case SUCCESS:{
				return BackupResult.SUCCESS;
			}
			case REQ_FILE_NOTEXIT:{
				return BackupResult.ERROR_NOLOG;
			}
			case CONN_ERROR:{
				return BackupResult.ERROR_CONNECT;
			}
			default:{
				return BackupResult.ERROR;
			}
		}
	}
}
