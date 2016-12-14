package com.yihuacomputer.fish.api.monitor.business;
/**
 * 应用状态
 * @author YiHua
 *
 */
public enum RunStatus {

	/**未知*/
	Unknown("RunStatus.Unknown","#a9a89d"),//gray
	
	/**初始化*/
	Initial("RunStatus.Initial","#4A65B5"),//blue
	
	/**正常服务*/
	Healthy("RunStatus.Healthy","#56cf3e"),//green
	
	/**半功能服务*/
	SubHealth("RunStatus.SubHealth","#F7F188"),//米黄色
	
	/**客户交易*/
	Customer("RunStatus.Customer","#0DBC03"),//深绿色
	
	/**维护*/
	Maintain("RunStatus.Maintain","#e4d603"),//orange
	
	/**厂商模式维护*/
	Vdm("RunStatus.Vdm","#F2BA8C"),	//淡褐色
	
	
	/**关机*/
	Halt("RunStatus.Halt","#AA0900"),//朱红色

	/**重启*/
	ReBoot("RunStatus.ReBoot","#436567"),	
	
	/**P端通讯故障*/
	StopAtmp("RunStatus.StopAtmp","#717832"),
	
	/**人工报停*/
	StopManmade("RunStatus.StopManmade","#585253"),
	
	/**模块故障暂停服务*/
	StopMod("RunStatus.StopMod","#da0404"),//red
	
	/**未加钞暂停服务*/
	StopUnCashIn("RunStatus.StopUnCashIn","#F2680D"),
	
	/**未知原因暂停服务*/
	StopUnKnown("RunStatus.StopUnKnown","#665144");

	private String text;
	private String color;
	
	private RunStatus(String text,String color) {
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public String getColor() {
		return color;
	}

}
