package com.yihuacomputer.fish.report.service.etl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.report.engine.IPdfReportService;
import com.yihuacomputer.fish.report.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class PdfReportServiceTest{
	
	@Autowired
	IPdfReportService pdfReportService;
	
	@Test
	public void createReportWeek(){
		//pdfReportService.generateWeekPDF(201636);
	}

}
