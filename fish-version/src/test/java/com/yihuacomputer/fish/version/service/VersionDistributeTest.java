package com.yihuacomputer.fish.version.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.VersionStatusDistribute;
import com.yihuacomputer.fish.version.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { H2TestConfig.class })
public class VersionDistributeTest {

	@Autowired
	private IVersionService versionService;

	@Test
	@Ignore
	public void test() {
		IFilter filter = new Filter();
		filter.eq("versionType", Long.parseLong("2"));
		filter.eq("versionId",1l);
		filter.eq("orgFlag","1-");
		filter.eq("taskStatus","-1");
//		Map<Long,VersionDistribute> list = versionService.getVersionDistribute(filter);
		List<VersionStatusDistribute> list = getVersionStatusDistribute(filter);
		System.out.println(JsonUtils.toJson(list));
	}
	public List<VersionStatusDistribute> getVersionStatusDistribute(IFilter filter){
		return versionService.getVersionStatusDistribute(filter);
	}
}
