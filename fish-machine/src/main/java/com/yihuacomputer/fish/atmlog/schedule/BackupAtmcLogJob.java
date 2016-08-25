package com.yihuacomputer.fish.atmlog.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;
import com.yihuacomputer.fish.api.atmlog.IDayBackupService;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.atmlog.job.BackupManager;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 定时备份atmc日志
 * @author xuxiang
 * @since 2.1.1.1
 */
public class BackupAtmcLogJob extends AbstractYihuaJob{
	
	private Logger logger = LoggerFactory.getLogger(BackupAtmcLogJob.class);
	
	private BackupManager backupManager;
	private IDayBackupService dayBackupService;
	private IDeviceService deviceService;
	private IAtmLogService atmLogService;
	
	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		logger.info("backup atm file ....");
		dayBackupService = super.getApplicationContext().getBean(IDayBackupService.class);
		deviceService = super.getApplicationContext().getBean(IDeviceService.class);
		atmLogService = super.getApplicationContext().getBean(IAtmLogService.class);
		backupManager = super.getApplicationContext().getBean(BackupManager.class);
		
		String today = DateUtils.getDate(new Date());
		String yestoday = DateUtils.getLastDate();
		IDayBackupLog dayBackup = dayBackupService.get(today);
		if(dayBackup!=null){
			return;
		}else{
			dayBackup = dayBackupService.make();
			dayBackup.setDate(today);
			dayBackup.setDoTime(DateUtils.getTimestamp(new Date()));
			dayBackup.setResult(DayBackupResult.DOING);
		}

		dayBackupService.save(dayBackup);

		try {
			dayBackup.setDeviceCount(this.doBackupJob(yestoday));
			dayBackup.setResult(DayBackupResult.SUCCESS);
		} catch (Exception e) {
			dayBackup.setResult(DayBackupResult.ERROR);
			logger.error(String.format("schedule execute exception![%s]", e));
		}
		dayBackup.setEndTime(DateUtils.getTimestamp(new Date()));
		dayBackupService.update(dayBackup);
		logger.info("backup atm file finished");
	}
	
	/**
	 * 执行某日备份工作
	 * @param date
	 */
	private int doBackupJob(String date) throws Exception{
		IFilter filter = new Filter();
		filter.eq("devType.devVendor.id", Long.parseLong("1"));//此处表示为怡化厂家设备
		List<IDevice> devices = deviceService.list(filter);

		List<BackupRule> atmLogs = new ArrayList<BackupRule>();
		int diableCount = 0;
		for(IDevice device:devices){
			//设备状态不为正常时不备份日志,
			if(!device.getStatus().equals(DevStatus.OPEN)){
				diableCount = diableCount+1;
				continue;
			}
			BackupRule rule = new BackupRule();
			IAtmLog log = atmLogService.make();

			log.setTerminalId(device.getTerminalId());
			log.setDoTimes(0);
			log.setBackupResult(BackupResult.UNDO);
			log.setDateTime(date);
			log.setLastDoDate(DateUtils.getTimestamp(new Date()));
			log.setLogSize(0);
			atmLogService.saveAtmLog(log);

			rule.setBackupDate(date);
			rule.setTerminalId(device.getTerminalId());
			rule.setTerminalIp(device.getIp().toString());
			atmLogs.add(rule);
		}
		if(!atmLogs.isEmpty()){
			backupManager.getBackupPrivilege();
			backupManager.dayBackup(atmLogs);
		}
		return devices.size()-diableCount;
	}

}
