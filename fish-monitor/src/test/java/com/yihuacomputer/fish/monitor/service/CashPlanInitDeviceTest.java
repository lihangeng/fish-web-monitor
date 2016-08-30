package com.yihuacomputer.fish.monitor.service;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.monitor.MySQLTestConfig;
/*@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)*/
public class CashPlanInitDeviceTest extends BindSessionInTest2 {

	/*@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;
	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;
	
	@Test
	public void test(){
		System.out.println(JsonUtils.toJson(cashInitPlanDeviceInfoService.listSelectAble(cashInitPlanInfoService.get(11))));
	}*/
}
