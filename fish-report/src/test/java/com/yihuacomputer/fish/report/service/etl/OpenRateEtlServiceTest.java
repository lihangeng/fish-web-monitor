package com.yihuacomputer.fish.report.service.etl;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class OpenRateEtlServiceTest {
	
	@Autowired
	private IAvgOpenRateEtlService avgOpenRateEtlService;
	
	@Autowired
	private IDayOpenRateService openRateService;
	
	@Autowired
	private IDeviceOpenRateEtlService deviceOpenRateEtlService;
	
	@Autowired
	private IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	
	@Autowired
	private IOrgOpenRateEtlService orgOpenRateEtlService;
	
	@Before
	public void setUp() throws Exception {
		for(int i =0 ; i < 5; i++){
			IDayOpenRate dor = openRateService.make();
			dor.setTerminalId("1234");
			dor.setStatDate(DateUtils.getDate(new Date()));
			dor.setOpenTimes(10003);
			dor.setHealthyTimeReal(9920);
			openRateService.save(dor);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpenRateEtl(){
		Date date = new Date();
		avgOpenRateEtlService.extractByDay(date);
		avgOpenRateEtlService.extractByWeek(date);
		avgOpenRateEtlService.extractByMonth(date);
				
		deviceOpenRateEtlService.extractByWeek(date);
		deviceOpenRateEtlService.extractByMonth(date);
		
		deviceTypeOpenRateEtlService.extractByWeek(date);
		deviceTypeOpenRateEtlService.extractByMonth(date);
		
		orgOpenRateEtlService.extractByWeek(date);
		orgOpenRateEtlService.extractByMonth(date);
	}
	
	@Test
	@Ignore
	public void testOpenRateEtlData(){
		Date date = new Date();
		int week = DateUtils.getWeek(date).intValue();
		int month = (int)DateUtils.getLongYM(date);
		Object [] objects = avgOpenRateEtlService.getWeekTotal(week);
		System.out.println(objects[0]);
		System.out.println(objects[1]);
		System.out.println(objects[2]);
		System.out.println("===============");
		objects = avgOpenRateEtlService.getMonthTotal(month);
		System.out.println(objects[0]);
		System.out.println(objects[1]);
		System.out.println(objects[2]);
		List<IAvgDayOpenRate> lists = avgOpenRateEtlService.getAvgDays(20160901, 20160930);
		for(IAvgDayOpenRate each : lists){
			System.out.println(each.getId());
			System.out.println(each.getOpenTimes());
			System.out.println(each.getHealthyTimeReal());
			System.out.println(each.getOpenRate());
			System.out.println("=========dddd=====");
		}
	}
	
	
}
