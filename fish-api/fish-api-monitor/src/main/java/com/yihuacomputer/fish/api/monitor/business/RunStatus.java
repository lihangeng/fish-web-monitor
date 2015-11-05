package com.yihuacomputer.fish.api.monitor.business;
/**
 * 应用状态
 * @author YiHua
 *
 */
public enum RunStatus {

	/**未知*/
	Unknown("RunStatus.Unknown","#ccccff"),
	
	/**初始化*/
	Initial("RunStatus.Initial","#006600"),
	
	/**正常服务*/
	Healthy("RunStatus.Healthy","#33ff00"),
	
	/**半功能服务*/
	SubHealth("RunStatus.SubHealth","#33CC99"),
	
	/**客户交易*/
	Customer("RunStatus.Customer","#66FF66"),
	
	/**维护*/
	Maintain("RunStatus.Maintain","#CCCC66"),
	
	/**厂商模式维护*/
	Vdm("RunStatus.Vdm","#CC6633"),	
	
	
	/**关机*/
	Halt("RunStatus.Halt","#000000"),

	/**重启*/
	ReBoot("RunStatus.ReBoot","#330066"),	
	
	/**P端通讯故障*/
	StopAtmp("RunStatus.StopAtmp","#FF0066"),
	
	/**人工报停*/
	StopManmade("RunStatus.StopManmade","#FF00FF"),
	
	/**模块故障暂停服务*/
	StopMod("RunStatus.StopMod","#FF0033"),
	
	/**未加钞暂停服务*/
	StopUnCashIn("RunStatus.StopUnCashIn","#FF6600"),
	
	/**未知原因暂停服务*/
	StopUnKnown("RunStatus.StopUnKnown","#FF0000");

	private String text;
	private String color;
	
	private RunStatus(String text,String color) {
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
