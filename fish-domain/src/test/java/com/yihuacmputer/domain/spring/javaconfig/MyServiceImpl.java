package com.yihuacmputer.domain.spring.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceImpl extends BaseMyServiceImpl {

	@Autowired
	private IHisService hisService;

	public String getWhat(){
		return hisService.getHisName();
	}

}
