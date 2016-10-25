package com.yihuacomputer.fish.report.service.etl;

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

import com.yihuacomputer.fish.api.report.fault.etl.IFaultExtractDataService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class FaultEtlServiceTest {
	
	@Autowired
	private IFaultExtractDataService faultExtractDataService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpenRateEtl(){
		Date date = new Date();
//		faultEtlService.extractStatusByWeek(date);
//		faultEtlService.extractStatusByMonth(date);
//		faultEtlService.extractFaultClassifyByWeek(date);
//		faultEtlService.extractFaultClassifyByMonth(date);
//		faultEtlService.extractDurationByWeek(date);
		faultExtractDataService.extractDurationByMonth(date);
	}
	
}
