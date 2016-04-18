package com.yihuacomputer.fish.parameter.publishjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;

public class AtmcParamsJobThread implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(AtmcParamsJobThread.class);

	private static final String PARAM_PUSH_URL = "/ctr/paramUpdateNotify";

	private IParamPublishResultService paramPublishResultService;

	private IDeviceService deviceService;

	private ParamPublishMsg msg;

	private IParamPublishResult result;

	public AtmcParamsJobThread() {

	}

	public AtmcParamsJobThread(IParamPublishResult result) {
		this.result = result;
	}

	@Override
	public void run() {
		IDevice device = null;
		try {
			device = deviceService.get(result.getDeviceId());
			logger.info(String.format("down params to [%s] start.",device.getTerminalId()));
			msg = new ParamPublishMsg(result) ;
			msg.setTerminalId(device.getTerminalId());
			msg.setRet("02");
			String url = getNoticetUrl(device.getIp());
			ParamPublishMsg responseMsg = (ParamPublishMsg) HttpProxy
					.httpPost(url, msg, ParamPublishMsg.class,30000);
			// 将下发结果回填至业务功能下发结果表
			if (responseMsg != null) {
				IParamPublishResult result = paramPublishResultService.get(msg.getTaskId()) ;
				if (result!=null) {
					result.setRet(StringUtils.isEmpty(responseMsg
							.getRet()) ? "02" : responseMsg.getRet());
					paramPublishResultService.update(result);
				}
			}
		} catch (Exception e) {
			logger.error(String.format("下发失败! 错误信息[%s]!", e));

			IParamPublishResult result = paramPublishResultService.get(msg.getTaskId()) ;
			if (result!=null) {
				result.setRet("01");
				paramPublishResultService.update(result);
			}
		}
	}
	private String getNoticetUrl(ITypeIP ip) {
		return MonitorCfg.getHttpUrl(ip.toString()) + PARAM_PUSH_URL;
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
