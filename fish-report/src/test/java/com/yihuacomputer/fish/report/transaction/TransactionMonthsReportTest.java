package com.yihuacomputer.fish.report.transaction;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;
import com.yihuacomputer.fish.api.report.base.ITransactionMonthsService;
import com.yihuacomputer.fish.report.MysqlTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class TransactionMonthsReportTest {

	@Autowired
	private ITransactionMonthsService transactionMonthsService;
	
	@Test
	@Ignore
	public void monthsLoad(){
		String date = "201605";
		transactionMonthsService.extractDate(date);
		List<ITransactionMonths> monthList = transactionMonthsService.list();
		System.out.println(JsonUtils.toJson(monthList));
	}
}
