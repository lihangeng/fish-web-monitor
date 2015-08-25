package com.yihuacomputer.fish.web.atm;

/**
 * 监控响应码
 * 
 * @author YiHua
 *
 */
public enum MonitorResponse {
	RET_00,//成功
	RET_01,//失败
	RET_02,//设备不存在
	RET_03,//设备未注册
	RET_04,//设备未签到
	RET_05//设备状态异常
}
