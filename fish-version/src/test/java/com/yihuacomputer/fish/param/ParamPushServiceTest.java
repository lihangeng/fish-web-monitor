package com.yihuacomputer.fish.param;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class ParamPushServiceTest extends BindSessionInTest2{
	@Autowired
	private IParamPublishService paramPushService;
	@Autowired
	private IParamPublishResultService paramPushResultService;
	@Autowired
	private IDeviceService deviceService;
	
	@org.junit.Test
	@Ignore
	public void Test(){
		IParamPublish paramPublish=paramPushService.make();
		paramPublish.setDate("123123");
		paramPublish.setPublisher(1l);//("123123");
		paramPublish.setRet("123123");
		paramPublish.setDate("123123");
		paramPushService.save(paramPublish);
		IParamPublishResult paramPublishResult = new ParamPublishResult();
		//TODO terminalId
		paramPublishResult.setDeviceId(1l);
		IDevice device= deviceService.get(1l);
		paramPublishResult.setParamPublish(paramPublish);
		paramPublishResult.setVersionNo(160415134145032l);
		paramPublishResult.setDevice(device);
		paramPushResultService.save(paramPublishResult);
//		paramPushService.noticeDeviceDownloadParamFileByTemplate(1l,1l,1460683241785l);
//		paramPushService.generateParamFileByTemplate(1l);
//		paramPushService.generateParamFileByDevice(3l);
	}
}
