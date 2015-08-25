package com.yihuacomputer.fish.fault.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.fault.IFaultFilter;
import com.yihuacomputer.fish.fault.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class FaultFilterTest {

	@Autowired
	private IFaultFilter faultFilter;

	@Test
	@Ignore
	public void test(){
		faultFilter.initFilterCode();
	}
}
