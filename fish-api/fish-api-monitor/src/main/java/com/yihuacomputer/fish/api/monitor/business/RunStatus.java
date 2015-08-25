package com.yihuacomputer.fish.api.monitor.business;
/**
 * 应用状态
 * @author YiHua
 *
 */
public enum RunStatus {

	/**未知*/
	Unknown("未知"),
	
	/**初始化*/
	Initial("初始化"),
	
	/**正常服务*/
	Healthy("正常"),
	
	/**半功能服务*/
	SubHealth("半功能"),
	
	/**客户交易*/
	Customer("交易"),
	
	/**维护*/
	Maintain("维护"),
	
	/**厂商模式维护*/
	Vdm("厂商模式"),	
	
	/**关机*/
	Halt("关机"),

	/**重启*/
	ReBoot("重启"),	
	
	/**P端通讯故障*/
	StopAtmp("交易前置故障"),
	
	/**人工报停*/
	StopManmade("报停"),
	
	/**模块故障暂停服务*/
	StopMod("暂停服务-模块故障"),
	
	/**未加钞暂停服务*/
	StopUnCashIn("暂停服务-未加钞"),
	
	/**未知原因暂停服务*/
	StopUnKnown("暂停服务");
	
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
