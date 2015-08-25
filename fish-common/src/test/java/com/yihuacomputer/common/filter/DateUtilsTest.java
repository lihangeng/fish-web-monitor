package com.yihuacomputer.common.filter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yihuacomputer.common.util.DateUtils;

public class DateUtilsTest {
	@Test
	public void testGetPreMinuteTimestamp(){
		
		String preDate = DateUtils.getPreMinuteTimestamp(30);
		System.out.println(preDate);
		
		assertFalse(preDate.equals("2012-03-14 09:28:37"));
	}
}
