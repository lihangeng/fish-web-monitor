package com.yihuacomputer.fish.report.service.etl;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class DeviceEtlServiceTest {

	@Autowired
	private IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	
	@Autowired
	private IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	
	@Autowired
	private IDeviceExtractDataMonthService deviceExtractDataMonthService;
	
	@Autowired
	private IDeviceExtractDataWeekService deviceExtractDataWeekService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tesCatalogMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 7);
		deviceExtractDataMonthService.loadCatalogBaseData(c.getTime());
	}

	@Test
	public void tesCatalogWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -35);
		for (int i = 5; i >= 0; i--) {
			System.out.println(DateUtils.getDate(c.getTime()));
			deviceExtractDataWeekService.loadCatalogBaseData(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 7);
		}
	}

	@Test
	public void testTypeMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 7);
		deviceExtractDataMonthService.loadTypeBaseData(c.getTime());
	}

	@Test
	public void testTypeWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -35);
		for (int i = 5; i >= 0; i--) {
			System.out.println(DateUtils.getDate(c.getTime()));
			deviceExtractDataWeekService.loadTypeBaseData(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 7);
		}
	}

}
