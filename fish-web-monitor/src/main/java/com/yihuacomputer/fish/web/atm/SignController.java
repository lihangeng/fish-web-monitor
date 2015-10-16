package com.yihuacomputer.fish.web.atm;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.atm.format.SignMsg;

/**
 * 提供ATM设备开机签到服务接口
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/atmsign")
public class SignController {

	private Logger logger = LoggerFactory.getLogger(SignController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IRegistService registSerivce;
	@Autowired
	private MessageSource messageSourceEnum;
	/**
	 * 接收开机签到请求
	 * @param signInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody SignMsg reciveMsg(@RequestBody SignMsg signInfo) {
		try{
			IDeviceRegister deviceRegister = this.registSerivce.load(signInfo.getTermId());

			if(deviceRegister==null){
				signInfo.setRegSn(null);
				signInfo.setRet("RET_03");
			}else{
				signInfo.setRegSn(deviceRegister.getRegisterSerialNumber());
				signInfo.setRet("RET_00");
				deviceRegister.setSignTimes(deviceRegister.getSignTimes()+1);
				deviceRegister.setAtmcVersion(signInfo.getAtmcVersion());
				deviceRegister.setRegStatus(signInfo.getRegStatus());

				registSerivce.update(deviceRegister);

				collectService.collectBootSign(signInfo.getTermId(), deviceRegister, messageSourceEnum);
				if(MonitorCfg.getCheckTime()){
					signInfo.setServerDateTime(DateUtils.getTimestamp(new Date()));
				}
			}
		}catch(Exception e){
			logger.error(String.format("处理签到信息异常![%s],请求信息[%s]",e,JsonUtils.toJson(signInfo)));
		}
		return signInfo;
	}
}
