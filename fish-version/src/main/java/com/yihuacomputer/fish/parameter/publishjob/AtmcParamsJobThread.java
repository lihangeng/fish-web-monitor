package com.yihuacomputer.fish.parameter.publishjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPulishResult;
import com.yihuacomputer.fish.parameter.entity.ParamPulishResult;

public class AtmcParamsJobThread implements Runnable {

	private static Logger logger = LoggerFactory
			.getLogger(AtmcParamsJobThread.class);

	private IParamPublishResultService paramPublishResultService;

	private IDeviceService deviceService;

	private ParamPublishMsg msg;

	private IParamPulishResult result;

	public AtmcParamsJobThread() {

	}

	public AtmcParamsJobThread(IParamPulishResult result) {
		this.result = result;
	}

	@Override
	public void run() {
		IDevice device = null;
//		try {
//			device = deviceService.get(result.getDeviceId());
//			logger.info(String.format("down params to [%s] start.",device.getTerminalId()));
//			msg = new ParamPublishMsg(result) ;
//			msg.setTerminalId(device.getTerminalId());
//			msg.setAppRet("02");
//			String url = MonitorCfg.getHttpUrl(device.getIp().toString())
//					+ "/ctr/atmcParamUpdate";
//			ParamPublishForm responseMsg = (ParamPublishForm) HttpProxy
//					.httpPost(url, msg, ParamPublishForm.class,30000);
//
//			// 将下发结果回填至业务功能下发结果表
//			if (responseMsg != null) {
//				IAtmcParamPublishResult result = atmcParamPublishResultService.get(msg.getTaskId()) ;
//				if (result!=null) {
//					result.setRet(StringUtils.isEmpty(responseMsg
//							.getAppRet()) ? "02" : responseMsg.getAppRet());
//					atmcParamPublishResultService.update(result);
//				}
//			}
//		} catch (Exception e) {
//			logger.error(String.format("下发失败! 错误信息[%s]!", e));
//
//			IAtmcParamPublishResult result = atmcParamPublishResultService.get(msg.getTaskId()) ;
//			if (result!=null) {
//				result.setRet("01");
//				atmcParamPublishResultService.update(result);
//			}
//		}
	}

	public IParamPublishResultService getAtmcParamPublishResultService() {
		return paramPublishResultService;
	}

	public void setParamPublishResultService(
			IParamPublishResultService atmcParamPublishResultService) {
		this.paramPublishResultService = atmcParamPublishResultService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
}
