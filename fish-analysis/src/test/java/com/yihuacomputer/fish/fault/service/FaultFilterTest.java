package com.yihuacomputer.fish.fault.service;

import java.util.Calendar;
import java.util.Date;

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
//		idcsms.make(); //monitor
//		idcsms.save(null);//analysis
//		idcsms.update(null);//null
//		idcsms.list(null);// null
//		idcsms.make();
//		idcsms.list(null);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 8);
		c.add(Calendar.DAY_OF_MONTH, -14);
		idcsms.loadBaseDate(c.getTime());
	}
}
