package com.yihuacomputer.fish.analysis.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.analysis.MySQLTestConfig;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryWeekService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class DeviceCatalogSummaryWeekTest {

	@Autowired
	private IDeviceCatalogSummaryWeekService idcsws;

	@Test
	public void test(){
//		idcsms.save(null);//analysis
//		idcsms.list(null);// null
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
//		c.add(Calendar.DAY_OF_MONTH, -21);
		idcsws.loadBaseDate(c.getTime());
	}
}
