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

	@Bean
	public IAtmLogInfoService atmLogInfoService(){
		return new AtmLogInfoService();
	}
	@Bean
	public IAtmLogService atmLogService(){
		return new AtmLogService();
	}
	@Bean
	public IDayBackupService dayBackupService(){
		return new DayBackupService();
	}
	@Bean
	public IAtmLogGlobalStatisticsService atmLogGlobalStatisticsService(){
		return new AtmLogGlobalStatisticsService();
	}

	@Bean(name = "backupAtmLogJob")
	public IDayBackupExcuter dayBackupExcuter(){
		return new DayBackupExcuter();
	}

	@Bean
	public IBackupFileCfg backupFileCfg(){
		return new BackupFileCfg();
	}
	@Bean
	public IJournalFileService journalFileService(){
		return new JournalFileService();
	}

	@Bean
	public JournalParser journalParser(){
		return new JournalParser();
	}

	@Bean
	public BackupManager backupManager(){
		return new BackupManager();
	}
}
