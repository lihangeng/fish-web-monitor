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

import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class RetainCardEtlServiceTest {
	
	@Autowired
	private IRetainCardEtlService retainCardEtlService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpenRateEtl(){
		Date date = new Date();
		retainCardEtlService.extractByWeek(date);
		retainCardEtlService.extractByMonth(date);
	}
	
}
