package com.yihuacmputer.domain.spring.javaconfig;

import java.util.Date;

import com.yihuacomputer.common.util.DateUtils;

public class T {

	public static void main(String[] args) {
		Long date = Long.parseLong(DateUtils.get(new Date(), DateUtils.STANDARD_DATE_SHORT));
		System.out.println(date.intValue()-7);
	}

}
