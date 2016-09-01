package com.yihuacomputer.fish.monitor.service;

import org.junit.runner.RunWith;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.H2TestConfig;
import com.yihuacomputer.fish.monitor.MySQLTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class CashInitPlanTest extends BindSessionInTest2{

	@Autowired
	private ICashInitPlanInfoService caseCashInitPlanInfoService;
	@Autowired
	private IOrganizationService organizationService;
	
	@Test
	@Ignore
	public void test(){
		IOrganization org =organizationService.get("1");
		caseCashInitPlanInfoService.generalCashInitPlan(org,"20160731");
	}
}/**/
