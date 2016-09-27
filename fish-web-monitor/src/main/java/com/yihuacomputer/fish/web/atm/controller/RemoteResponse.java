package com.yihuacomputer.fish.web.atm.controller;

/**
 * Remote响应码
 * @author YiHua
 *
 */
public enum RemoteResponse {
	RET00,//成功
	RET01,//失败
	RET02,//ATMC应用忙
	RET03,//Agent异常
	RET04,//下发成功
	RET05,//下发失败
	RET06,//部署成功
	RET07//部署失败
}
