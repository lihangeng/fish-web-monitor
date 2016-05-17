package com.yihuacomputer.fish.web;

import java.util.Date;

import com.yihuacomputer.common.util.DateUtils;

public class T {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		System.out.print(new Date(138454418820300l));
		for(int i =0 ;i < 1 ;i ++){
			long s = System.currentTimeMillis();
			System.out.println(s);
			System.out.println(DateUtils.getTimestamp(new Date(s)));
			Date d = new Date();
			System.out.println(d.getTime());
			System.out.println(d);
			System.out.println("------------------------------");
			Thread.sleep(5000);
		}
	}

}
