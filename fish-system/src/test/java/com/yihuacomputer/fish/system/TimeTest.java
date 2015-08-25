package com.yihuacomputer.fish.system;

import java.util.Calendar;

import com.yihuacomputer.common.util.DateUtils;

public class TimeTest {

	public static void main(String[] args){
		Calendar dateTime = Calendar.getInstance();
		long doTime = DateUtils.getTimestamp("2015-07-13 11:16:00").getTime();

		
		long subTime = Math.abs(dateTime.getTimeInMillis() -doTime);
		
        long diffMinutes = subTime / (60 * 1000) % 60;
        long diffHours = subTime / (60 * 60 * 1000) % 24;
        long diffDays = subTime / (24 * 60 * 60 * 1000);
        
        System.out.println(String.format("%s    %s    %s",diffDays,diffHours,diffMinutes));
	}
}
