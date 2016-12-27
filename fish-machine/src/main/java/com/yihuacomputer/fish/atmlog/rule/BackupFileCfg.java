package com.yihuacomputer.fish.atmlog.rule;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.fish.api.atmlog.AtmLogCfg;
import com.yihuacomputer.fish.api.atmlog.IBackupFileCfg;
import com.yihuacomputer.fish.api.atmlog.IBackupRule;

/**
 * @author YiHua
 *
 */
@Service
public class BackupFileCfg implements IBackupFileCfg{

	@Override
	public String getServerFilePath(IBackupRule backupRule) {
		return AtmLogCfg.getAtmAppLogDir() + FishCfg.FILESEP
				+ backupRule.getBackupDate().substring(0, 4) + FishCfg.FILESEP
				+ backupRule.getBackupDate().substring(5, 7) + FishCfg.FILESEP
				+ backupRule.getTerminalId();
	}

	@Override
	public String getServerFileName(IBackupRule backupRule) {
		String fileName  = AtmLogCfg.getAtmLogFileCfg();

		fileName = StringUtils.replaceLogRule(fileName, "\\{terminalId\\}", backupRule.getTerminalId());
		fileName = StringUtils.replaceLogRule(fileName, "\\{YYYYMMDD\\}", StringUtils.replaceLogRule(backupRule.getBackupDate(),"-",""));
		fileName = StringUtils.replaceLogRule(fileName, "\\{YYYY-MM-DD\\}", backupRule.getBackupDate());
		return fileName;
	}

	@Override
	public String getAtmFilePath(IBackupRule backupRule) {
		return AtmLogCfg.getAtmLogDoc();
	}

	@Override
	public String getAtmFileName(IBackupRule backupRule) {
		return this.getServerFileName(backupRule);
	}

}
