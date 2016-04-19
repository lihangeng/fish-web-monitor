package com.yihuacomputer.fish.parameter.publishjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.ParamPublishMsg;

public class AtmcParamsJobThread implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(AtmcParamsJobThread.class);


	private ParamPublishMsg msg;

	private IParamPublishResult result;

	private IParamPublishResultService atmcParamPublishResultService;
	

	public AtmcParamsJobThread() {

	}

	public AtmcParamsJobThread(IParamPublishResult result,
			IParamPublishResultService atmcParamPublishResultService
			) {
		this.result = result;
		this.atmcParamPublishResultService = atmcParamPublishResultService;
	}

	@Override
	public synchronized void run() {
		IDevice device = null;
		try {
			device = result.getDevice();
			logger.info(String.format("down params to [%s] start.",device.getTerminalId()));
			msg = new ParamPublishMsg(result) ;
			msg.setTerminalId(device.getTerminalId());
			msg.setRet("02");
			if(atmcParamPublishResultService.notice(msg,device)){
				logger.error(String.format("%s publish failer!",device.getTerminalId()));
			}
		} catch (Exception e) {
			logger.error(String.format("下发失败! 错误信息[%s]!", e));
		}
	}

	
}
