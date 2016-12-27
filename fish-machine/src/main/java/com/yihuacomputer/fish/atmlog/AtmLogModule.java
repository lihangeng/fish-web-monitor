package com.yihuacomputer.fish.atmlog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.atmlog.IAtmLogGlobalStatisticsService;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfoService;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupFileCfg;
import com.yihuacomputer.fish.api.atmlog.IDayBackupExcuter;
import com.yihuacomputer.fish.api.atmlog.IDayBackupService;
import com.yihuacomputer.fish.api.atmlog.IJournalFileService;
import com.yihuacomputer.fish.atmlog.job.BackupManager;
import com.yihuacomputer.fish.atmlog.parse.JournalFileService;
import com.yihuacomputer.fish.atmlog.parse.JournalParser;
import com.yihuacomputer.fish.atmlog.rule.BackupFileCfg;
import com.yihuacomputer.fish.atmlog.schedule.DayBackupExcuter;
import com.yihuacomputer.fish.atmlog.service.db.AtmLogGlobalStatisticsService;
import com.yihuacomputer.fish.atmlog.service.db.AtmLogInfoService;
import com.yihuacomputer.fish.atmlog.service.db.AtmLogService;
import com.yihuacomputer.fish.atmlog.service.db.DayBackupService;

/**
 * ATM日志模块配置
 * @author xuxiang
 * @since 1.4.0
 */
@Configuration
public class AtmLogModule {

	/**
	 * @return
	 */
	@Bean
	public IAtmLogInfoService atmLogInfoService(){
		return new AtmLogInfoService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAtmLogService atmLogService(){
		return new AtmLogService();
	}
	/**
	 * @return
	 */
	@Bean
	public IDayBackupService dayBackupService(){
		return new DayBackupService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAtmLogGlobalStatisticsService atmLogGlobalStatisticsService(){
		return new AtmLogGlobalStatisticsService();
	}

	/**
	 * @return
	 */
	@Bean(name = "backupAtmLogJob")
	public IDayBackupExcuter dayBackupExcuter(){
		return new DayBackupExcuter();
	}

	/**
	 * @return
	 */
	@Bean
	public IBackupFileCfg backupFileCfg(){
		return new BackupFileCfg();
	}
	/**
	 * @return
	 */
	@Bean
	public IJournalFileService journalFileService(){
		return new JournalFileService();
	}

	/**
	 * @return
	 */
	@Bean
	public JournalParser journalParser(){
		return new JournalParser();
	}

	/**
	 * @return
	 */
	@Bean
	public BackupManager backupManager(){
		return new BackupManager();
	}
}
