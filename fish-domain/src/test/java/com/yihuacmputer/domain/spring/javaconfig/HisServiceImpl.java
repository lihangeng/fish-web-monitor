package com.yihuacmputer.domain.spring.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;

public class HisServiceImpl implements IHisService{

	@Autowired
	private IMyChildService childService;

	@Override
	public String getHisName() {
		return "his name is : "+childService.getName();
	}

}
