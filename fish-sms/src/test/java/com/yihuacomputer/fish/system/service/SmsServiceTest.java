package com.yihuacomputer.fish.system.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.ISMSLibMessageService;
import com.yihuacomputer.fish.system.MySQLTestConfig;

/**
 * 账户信息服务单元测试
 * @author xuxigang
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySQLTestConfig.class)
public class SmsServiceTest extends BindSessionInTest2
{
    @Autowired
    private ISMSLibMessageService smsLibMessageService;

    @Test
    @Ignore
    public void test(){
    	smsLibMessageService.sendMsg("18724004683", "test短信","COM3");
    	try {
			Thread.currentThread().sleep(160L*1000L*1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    

}
