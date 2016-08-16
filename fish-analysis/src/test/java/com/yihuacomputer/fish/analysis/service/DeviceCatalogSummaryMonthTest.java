/*package com.yihuacomputer.fish.analysis.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.analysis.MySQLTestConfig;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class DeviceCatalogSummaryMonthTest {

	@Autowired
	private IDeviceCatalogSummaryMonthService idcsms;

	@Test
	public void test(){
//		idcsms.save(null);//analysis
//		idcsms.list(null);// null
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 7);
//		c.add(Calendar.DAY_OF_MONTH, -14);
		idcsms.loadBaseDate(c.getTime());
	}
}
*/