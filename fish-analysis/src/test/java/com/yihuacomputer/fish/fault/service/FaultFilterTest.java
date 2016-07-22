package com.yihuacomputer.fish.fault.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.fault.MySQLTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class FaultFilterTest {

	@Autowired
	private IDeviceCatalogSummaryMonthService idcsms;

	@Test
	public void test(){
		idcsms.make();
		idcsms.save(null);
		idcsms.list(null);
		idcsms.make();
		idcsms.list(null);
	}
}
