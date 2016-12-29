/**
 * 
 */
package com.yihuacomputer.fish.web.mock;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化Fish运行环境
 * @author xuxigang
 *
 */
public class FishInit {
	
	@Autowired
	private PersonDataLoader personDataLoader;
	@Autowired
	private MachineDataLoader machineDataLoader;
	@Autowired
	private VersionDataLoader versionDataLoader;
	@Autowired
	private	RetaincardDataLoader retaincardDataLoader;

	@Autowired
	private ParmDataLoader parmDataLoader;
	
	
	/**
	 *初始化 
	 */
	public void init(){
		parmDataLoader.init();
		personDataLoader.init();
		machineDataLoader.init();
		versionDataLoader.init();
		retaincardDataLoader.init();
	
	}	
	
}
