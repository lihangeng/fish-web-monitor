package com.yihuacomputer.fish.atmlog.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atmlog.BackupResult;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IBackupRule;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.atmlog.job.BackupManager;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;
import com.yihuacomputer.fish.atmlog.service.db.AtmLogService;

/**
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = H2TestConfig.class)
public class AtmLogServiceTest extends BindSessionInTest2 {

//	@Autowired
	private BackupManager backupManager;
//	@Autowired
	private AtmLogService atmLogService;

	@Test
	@Ignore
	public void testAdd() {
		List<BackupRule> atmLogs = new ArrayList<BackupRule>();

		for (int i = 0; i < 1000; i++) {
			BackupRule rule = new BackupRule();
			rule.setBackupDate("2012-03-12");
			rule.setTerminalId("A1001");
			rule.setTerminalIp("192.168.0.33");
			atmLogs.add(rule);
		}
		if (!atmLogs.isEmpty()) {
			backupManager.getBackupPrivilege();

			try {
				backupManager.dayBackup(atmLogs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试这个需要关闭scan-hibernate.xml中jobManager的配置
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@Test
	@Ignore
	public void testLoadErrorLogs() {
		IAtmLog log = atmLogService.make();
		log.setBackupResult(BackupResult.UNDO);
		log.setDoTimes(5);
		log.setLastDoDate("2012-03-12");
		log.setTerminalId("0001");
		atmLogService.saveAtmLog(log);

		log = atmLogService.make();
		log.setBackupResult(BackupResult.UNDO);
		log.setDoTimes(6);
		log.setLastDoDate("2012-03-12");
		log.setTerminalId("0001");
		atmLogService.saveAtmLog(log);

		List<IBackupRule> rules = atmLogService.loadErrorLogs();

		IPageResult page = atmLogService.page(0, 1, new Filter());
		assertEquals(2,page.getTotal());
		List lists = page.list();
		for(Object obj : lists){
			System.out.println(obj);
		}
	}

}
