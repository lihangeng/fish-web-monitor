package com.yihuacomputer.fish.advert.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.advert.entity.Advert;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertService;

@Component("advertEntityInjector")
public class AdvertEntityInjector implements IEntityInjector {
	@Autowired
	private IDomainAdvertService advertService;

	@Override
	public void injectDependencies(Object entity) {
		if(entity instanceof Advert){
			Advert advert = (Advert)entity;
			advert.setAdvertService(advertService);
		}
	}
}
