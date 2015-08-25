package com.yihuacomputer.fish.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.system.im.IAnnouncementService;
import com.yihuacomputer.fish.api.system.im.IMessageService;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;
import com.yihuacomputer.fish.api.system.sms.IShortMessageService;
import com.yihuacomputer.fish.system.service.db.AnnouncementService;
import com.yihuacomputer.fish.system.service.db.JobSynchService;
import com.yihuacomputer.fish.system.service.db.MessageService;
import com.yihuacomputer.fish.system.service.db.ParamService;
import com.yihuacomputer.fish.system.service.db.ShortMessageService;

@Configuration
public class SystemModule {
	@Bean
	public IAnnouncementService announcementService() {
		return new AnnouncementService();
	}

	@Bean
	public IMessageService messageService() {
		return new MessageService();
	}

	@Bean
	public IParamService paramService() {
		return new ParamService();
	}

	@Bean
	public IShortMessageService shortMessageService() {
		return new ShortMessageService();
	}
	@Bean
	public IJobSynchService jobSynchService(){
		return new JobSynchService();
	}
}
