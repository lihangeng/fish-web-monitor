/*package com.yihuacomputer.fish.report.transaction;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.report.MysqlTestConfig;


@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class CaseFaultMonthReportTest {

	@Autowired
	private IEveryMonthFaultCountService monthFaultService;
	
	@Test
	public void daysLoad(){
		String date = "201605";
		monthFaultService.extractMonthFault(date);
	}
}
*/