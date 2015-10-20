package com.yihuacomputer.fish.advert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.advert.interceptor.AdvertEntityInjector;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertService;
import com.yihuacomputer.fish.advert.service.db.AdvertResourceService;
import com.yihuacomputer.fish.advert.service.db.AdvertService;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;

@Configuration
public class AdvertModule {
	@Bean
	public IAdvertResourceService advertResourceService() {
		return new AdvertResourceService();
	}

	@Bean
	public IDomainAdvertService advertService() {
		return new AdvertService();
	}

	@Bean
	public AdvertEntityInjector advertEntityInjector() {
		return new AdvertEntityInjector();
	}
}
