package com.yihuacomputer.fish.api.monitor.business;
/**
 * 应用状态
 * @author YiHua
 *
 */
public enum RunStatus {

	/**未知*/
	Unknown("RunStatus.Unknown"),
	
	/**初始化*/
	Initial("RunStatus.Initial"),
	
	/**正常服务*/
	Healthy("RunStatus.Healthy"),
	
	/**半功能服务*/
	SubHealth("RunStatus.SubHealth"),
	
	/**客户交易*/
	Customer("RunStatus.Customer"),
	
	/**维护*/
	Maintain("RunStatus.Maintain"),
	
	/**厂商模式维护*/
	Vdm("RunStatus.Vdm"),	
	
	/**关机*/
	Halt("RunStatus.Halt"),

	/**重启*/
	ReBoot("RunStatus.ReBoot"),	
	
	/**P端通讯故障*/
	StopAtmp("RunStatus.StopAtmp"),
	
	/**人工报停*/
	StopManmade("RunStatus.StopManmade"),
	
	/**模块故障暂停服务*/
	StopMod("RunStatus.StopMod"),
	
	/**未加钞暂停服务*/
	StopUnCashIn("RunStatus.StopUnCashIn"),
	
	/**未知原因暂停服务*/
	StopUnKnown("RunStatus.StopUnKnown");
	
	private String text;
	
	private RunStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
