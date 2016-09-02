package com.yihuacomputer.fish.report.pdf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.engine.IPdfReportService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.report.MysqlTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class ReportTest{
	@Autowired
	IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	@Autowired
	IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	@Autowired
	IAvgOpenRateEtlService avgOpenRateEtlService;
	@Autowired
	ITransTypeEtlService transTypeEtlService;
	@Autowired
	IRetainCardEtlService retainCardEtlService;
	@Autowired
	IFaultEtlService faultEtlService;
	@Autowired
	IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	@Autowired
	IOrgOpenRateEtlService orgOpenRateEtlService;
	@Autowired
	IDeviceOpenRateEtlService deviceOpenRateEtlService;
	@Autowired
	IPdfReportService pdfReportService;
	int weeks=36;
	@Test
	 public void createReportWeek(){
		pdfReportService.generateWeekPDF(2);
			
	}
	

}
