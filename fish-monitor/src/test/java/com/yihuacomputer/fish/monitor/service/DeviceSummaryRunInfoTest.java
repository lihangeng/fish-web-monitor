package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
import com.yihuacomputer.fish.monitor.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class DeviceSummaryRunInfoTest {
	
	@Autowired
	private IXfsChartService xfsChartService;
	
	@Test
	public void getSummaryInfo(){
		IFilter filter = new Filter();
		filter.eq("orgFlag", "-1");
//		List<Object> list = xfsChartService.getAllDeviceList(filter);
//		System.out.println(list.size());
		List<Object> summarylist = xfsChartService.getDeviceSummaryRunInfo(filter);
		System.out.println(summarylist.size());
//		List<Object> appList = xfsChartService.getDeviceAppRunInfo(filter);
//		System.out.println(appList.size());
//		List<Object> boxlist = xfsChartService.getDeviceBoxRunInfo(filter);
//		System.out.println(boxlist.size());
//		List<Object> Modlist = xfsChartService.getDeviceModRunInfo(filter);
//		System.out.println(Modlist.size());
//		List<Object> listNet = xfsChartService.getDeviceNetRunInfo(filter);
//		System.out.println(listNet.size());
	}
}
