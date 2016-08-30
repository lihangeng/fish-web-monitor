package com.yihuacomputer.fish.report.service.openrate.etl;

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
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AvgOpenRateEtlServiceTest {
	
	@Autowired
	private IAvgOpenRateEtlService avgOpenRateEtlService;
	
	@Autowired
	private IDayOpenRateService openRateService;
	
	@Before
	public void setUp() throws Exception {
		for(int i =0 ; i < 5; i++){
			IDayOpenRate dor = openRateService.make();
			dor.setTerminalId("1234");
			dor.setStatDate(DateUtils.getDate(new Date()));
			dor.setOpenTimes(10000);
			dor.setHealthyTimeReal(9900);
			openRateService.save(dor);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAvgDay(){
		Date date = new Date();
		avgOpenRateEtlService.extractByDay(date);
		avgOpenRateEtlService.extractByWeek(date);
		avgOpenRateEtlService.extractByMonth(date);
	}
	
}
