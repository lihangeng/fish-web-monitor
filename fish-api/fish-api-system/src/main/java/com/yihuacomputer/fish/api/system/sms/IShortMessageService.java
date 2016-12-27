package com.yihuacomputer.fish.api.system.sms;




/**
 * @author YiHua
 *
 */
public interface IShortMessageService {
	
	/**
	 * @return
	 */
	public IShortMessage make();

	/**
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public boolean send(IShortMessage message)throws Exception;
	
}
