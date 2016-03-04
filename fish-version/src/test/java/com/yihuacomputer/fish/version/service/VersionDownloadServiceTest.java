package com.yihuacomputer.fish.version.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.version.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class VersionDownloadServiceTest {
	@Autowired
	private IVersionDownloadService versionDownloadService;
	@Autowired
	private IVersionService versionService;
	@Test
	@Ignore
	public void test(){
//		IVersion version = versionService.getById(1l);
//		if(null!=version)
//			versionDownloadService.pageDownLoadDevices(1, 25, version, new Filter());
	}
}
