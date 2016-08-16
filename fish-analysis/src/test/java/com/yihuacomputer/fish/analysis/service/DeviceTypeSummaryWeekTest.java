/*package com.yihuacomputer.fish.analysis.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.analysis.MySQLTestConfig;
import com.yihuacomputer.fish.api.analysis.device.IDeviceTypeSummaryWeekService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class DeviceTypeSummaryWeekTest {
	@Autowired
	private IDeviceTypeSummaryWeekService idtsws;

	@Test
	public void test(){
//		idcsms.save(null);//analysis
//		idcsms.list(null);// null
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -35);
		for(int i=5;i>=0;i--){
			System.out.println(DateUtils.getDate(c.getTime()));
			idtsws.loadBaseDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 7);
		}
	}
}
*/