package com.yihuacomputer.fish.sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.system.sms.ISMSLibMessageService;
import com.yihuacomputer.fish.sms.impl.SMSLibMessageService;

@Configuration
public class SMSModule {
	@Bean
	public ISMSLibMessageService smsLibMessageService() {
		return new SMSLibMessageService();
	}

}