package com.yihuacomputer.fish.fault.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.fault.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class CaseCoreTest {

	@Autowired
	private ICaseFaultService icfs;

	@Test
	@Ignore
	public void test(){
		ICaseFault icf = icfs.getFault(1);
		List<IPerson> list = icf.getBankPerson();
		Assert.assertEquals(list.size(), 0);

	}
}
