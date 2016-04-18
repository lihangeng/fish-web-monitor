package com.yihuacomputer.fish.param;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class ParamPushServiceTest extends BindSessionInTest2{
	@Autowired
	private IParamPublishService paramPushService;
	
	@org.junit.Test
	@Ignore
	public void Test(){
		IParamPublish paramPublish=paramPushService.make();
		paramPublish.setDate("123123");
		paramPublish.setPublisher(1l);//("123123");
		paramPublish.setRet("123123");
//		paramPublish.setDate("123123");
		paramPushService.noticeDeviceDownloadParamFileByTemplate(1l,1l,1460683241785l);
//		paramPushService.generateParamFileByTemplate(2l);
//		paramPushService.generateParamFileByDevice(3l);
	}
}
