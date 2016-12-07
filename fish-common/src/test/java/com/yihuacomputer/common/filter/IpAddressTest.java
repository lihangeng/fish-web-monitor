package com.yihuacomputer.common.filter;

import com.yihuacomputer.common.FishCfg;

public class IpAddressTest {

	public static void main(String[] args){
		
		FishCfg.initDirs();
		System.out.print(FishCfg.getHostIp());
	}
}
