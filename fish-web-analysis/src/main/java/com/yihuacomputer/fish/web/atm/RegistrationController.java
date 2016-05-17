package com.yihuacomputer.fish.web.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.MsgDigestAlgorithm;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.web.atm.format.RegistrationMsg;


/**
 * 提供处理设备Agent注册服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * */


@Controller
@RequestMapping("/msg/registration")
public class RegistrationController {

	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private IRegistService registSerivce;	
	
	@Autowired
	private ICollectService collectService;
	
	/**
	 * 接收注册信息
	 * @param registration
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody RegistrationMsg reciveMsg(@RequestBody RegistrationMsg registration) {	
		try{
			IDeviceRegister deviceRegister = this.registSerivce.load(registration.getTermId());
			if(deviceRegister==null){
			    logger.warn(String.format("deviceRegister[%s] is null ",registration.getTermId()));
				registration.setRet("RET_02");
			}else{			
				deviceRegister.setRegTimes(deviceRegister.getRegTimes()+1);
				deviceRegister.setRegisterSerialNumber(MsgDigestAlgorithm.getMD5Str(registration.getRegId()));
				collectService.collectSetup(registration.getTermId(), deviceRegister);
				registration.setRegSn(deviceRegister.getRegisterSerialNumber());
				registration.setRet("RET_00");
			}	
		}catch(Exception e){
//			logger.error(String.format("处理注册请求异常![%s],请求信息[%s]",e,JsonUtils.toJson(registration)));
			logger.error(String.format("collection register info exception![%s],register info is [%s]",e,JsonUtils.toJson(registration)));
		}
		return registration;
	}
   
}
