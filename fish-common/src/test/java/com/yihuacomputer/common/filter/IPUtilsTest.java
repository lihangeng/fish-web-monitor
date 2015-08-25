package com.yihuacomputer.common.filter;

import java.util.List;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.util.IPUtils;

public class IPUtilsTest {

	public static void main(String[] args){
		
		List<ITypeIP> ips = IPUtils.listLocalIp();
		System.out.println(ips.toString());
	}
}
