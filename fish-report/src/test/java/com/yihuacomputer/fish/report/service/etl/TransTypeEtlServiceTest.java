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

import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeExtractDataService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class TransTypeEtlServiceTest {
	
	@Autowired
	private ITransTypeExtractDataService transTypeExtractDataService;
	
	@Autowired
	private ITransactionService transService;
	
	@Before
	public void setUp() throws Exception {
		for(int i =0 ; i < 5; i++){
			ITransaction trans = transService.make();
			trans.setDateTime(new Date());
			trans.setAmt(200);
			trans.setTerminalId("1234");
			trans.setLocalRet("00");
			trans.setHostRet("00");
			trans.setTransCode("CWD");
			trans.setTransId("100");
			transService.save(trans);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpenRateEtl(){
		Date date = new Date();
		transTypeExtractDataService.extractByDay(date);
		transTypeExtractDataService.extractByWeek(date);
		transTypeExtractDataService.extractByMonth(date);
	}
	
}
