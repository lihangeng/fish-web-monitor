package com.yihuacomputer.fish.param;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.parameter.IParamPushService;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class ParamPushServiceTest extends BindSessionInTest2{
	@Autowired
	private IParamPushService paramPushService;
	
	@org.junit.Test
	@Ignore
	public void Test(){
		paramPushService.noticeDeviceDownloadParamFileByDevice(1l);
	}
}
