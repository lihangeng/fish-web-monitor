/*package com.yihuacomputer.fish.monitor.service;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.monitor.H2TestConfig;
//import com.yihuacomputer.fish.monitor.MySQLTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class MonthDailyTradingVolumeServiceTest extends BindSessionInTest2{
	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;
	
	@org.junit.Test
	@Ignore
	public void Test(){
		monthDailyTradingVolumeService.generalMonthDailyTradingVolumeByDate("20160709");
	}
}
*/