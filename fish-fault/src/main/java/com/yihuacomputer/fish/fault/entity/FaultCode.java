package com.yihuacomputer.fish.fault.entity;

import com.yihuacomputer.fish.api.fault.IFaultCode;

/**
 * @author YiHua
 *
 */
public class FaultCode implements IFaultCode{
	
	private String faultCode;
	
	private String hwCode;
	
	@Override
	public String getFaultCode() {
		return this.faultCode;
	}

	@Override
	public String getHwCode() {
		return this.hwCode;
	}

}
