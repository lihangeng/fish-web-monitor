package com.yihuacomputer.fish.atmlog.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.DayBackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IDayBackupExcuter;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;
import com.yihuacomputer.fish.api.atmlog.IDayBackupService;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;
import com.yihuacomputer.fish.atmlog.entity.AtmLog;
import com.yihuacomputer.fish.atmlog.job.BackupManager;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * 启动每日日志备份工作
 *
 * @author YiHua
 */
@Service("backupAtmLogJob")
public class DayBackupExcuter implements IDayBackupExcuter{

	private Logger logger = LoggerFactory.getLogger(DayBackupExcuter.class);

	private final String jobId = "backuptask";

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IAtmLogService atmLogService;

	@Autowired
	private IDayBackupService dayBackupService;

	@Autowired
	private BackupManager backupManager;

    @Autowired
    private IJobSynchService jobSynchService;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 重做某日失败的日志任务
	 * @param date
	 * @throws
	 */
	public void redoDayJob(String date) {
		IDayBackupLog dayBackup = this.dayBackupService.get(date);
		if(dayBackup==null){
			dayBackup = this.dayBackupService.make();
			dayBackup.setResult(DayBackupResult.DOING);
			dayBackup.setDate(date);
			dayBackup.setDoTime(DateUtils.getTimestamp(new Date()));
			this.dayBackupService.save(dayBackup);
			Date today = new Date();
			Calendar c = Calendar.getInstance();
			try {
				today = new SimpleDateFormat("yy-MM-dd").parse(date);
			} catch (ParseException e) {
				dayBackup.setResult(DayBackupResult.ERROR);
				logger.error(String.format("ParseTimeException![%s]", e));
			}
			c.setTime(today);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE,day-1);
			String dayBefore=formatter.format(c.getTime());
			try {
				dayBackup.setDeviceCount(this.doBackupJob(dayBefore));
				dayBackup.setResult(DayBackupResult.SUCCESS);
			} catch (Exception e) {
				dayBackup.setResult(DayBackupResult.ERROR);
				logger.error(String.format("backup file exception![%s]", e));
			}
			dayBackup.setEndTime(DateUtils.getTimestamp(new Date()));
			this.dayBackupService.update(dayBackup);
		}else{
			if(dayBackup.getResult().equals(DayBackupResult.SUCCESS)){
				return;
			}
			dayBackup.setResult(DayBackupResult.DOING);
			dayBackup.setDoTime(DateUtils.getTimestamp(new Date()));

			this.dayBackupService.update(dayBackup);

			List<Object> result = this.atmLogService.loadDayErrorLogs(date);

			try {
				this.redoBackupJob(date,result);
				dayBackup.setResult(DayBackupResult.SUCCESS);
			} catch (Exception e) {
				dayBackup.setResult(DayBackupResult.ERROR);
				logger.error(String.format("redo backup task exception![%s]", e));
			}
			dayBackup.setEndTime(DateUtils.getTimestamp(new Date()));
			this.dayBackupService.update(dayBackup);
		}
	}

	/**
	 * 定时任务调用执行每日备份工作
	 * @throws Exception
	 */
	public void backupAtmLog(){
		logger.info("backup atm file ....");
    	if(!jobSynchService.getJobRunPriviledge(jobId)){
    		logger.info("backup atm file job is running");
    		return;
    	}

		String today = DateUtils.getDate(new Date());
		String yestoday = DateUtils.getLastDate();

		IDayBackupLog dayBackup = this.dayBackupService.get(today);

		if(dayBackup!=null){
			return;
		}else{
			dayBackup = this.dayBackupService.make();
			dayBackup.setDate(today);
			dayBackup.setDoTime(DateUtils.getTimestamp(new Date()));
			dayBackup.setResult(DayBackupResult.DOING);
		}

		this.dayBackupService.save(dayBackup);

		try {
			dayBackup.setDeviceCount(this.doBackupJob(yestoday));
			dayBackup.setResult(DayBackupResult.SUCCESS);
		} catch (Exception e) {
			dayBackup.setResult(DayBackupResult.ERROR);
//			logger.error(String.format("定时调度出错![%s]", e));
			logger.error(String.format("schedule execute exception![%s]", e));
		}
		dayBackup.setEndTime(DateUtils.getTimestamp(new Date()));
		this.dayBackupService.update(dayBackup);
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

	/**
	 * 重做执行某日备份工作
	 * @param date
	 */
	private void redoBackupJob(String date,List<Object> devices) throws Exception{

		List<BackupRule> atmLogs = new ArrayList<BackupRule>();

		for(Object obj:devices){

			Object[] device = (Object[])obj;

			IAtmLog log = (AtmLog)device[0];

			String ip = String.valueOf(device[1]);

			BackupRule rule = new BackupRule();
			log.setBackupResult(BackupResult.UNDO);
			log.setLastDoDate(DateUtils.getTimestamp(new Date()));
			atmLogService.updateAtmLog(log);

			rule.setBackupDate(date);
			rule.setTerminalId(log.getTerminalId());
			rule.setTerminalIp(ip);
			atmLogs.add(rule);
		}
		if(!atmLogs.isEmpty()){
			backupManager.getBackupPrivilege();

			backupManager.dayBackup(atmLogs);
		}
	}
}
