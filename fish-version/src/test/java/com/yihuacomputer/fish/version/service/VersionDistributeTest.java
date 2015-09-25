package com.yihuacomputer.fish.version.service;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.VersionDistribute;
import com.yihuacomputer.fish.version.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { H2TestConfig.class })
public class VersionDistributeTest {

	@Autowired
	private IVersionService versionService;

	@Test
	public void test() {
		IFilter filter = new Filter();
		filter.eq("versionType", Long.parseLong("2"));
		Map<Long,VersionDistribute> list = versionService.getVersionDistribute(filter);
		System.out.println(JsonUtils.toJson(list));
	}

}
