package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public interface IHostRetService {

	/**
	 * @return
	 */
	public IHostRet make();
	
	/**
	 * @param hostRet
	 */
	public void save(IHostRet hostRet);
}
