package com.yihuacomputer.fish.web.atm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.CardRetainType;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.web.atm.format.RetaincardMsg;

/**
 * 提供设备吞卡处理服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/retaincard")
public class DeviceRetaincardController {
	
	private Logger logger = LoggerFactory.getLogger(DeviceRetaincardController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IRetaincardService retainService;

	/**
	 * 接受吞卡信息
	 * @param runInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody RetaincardMsg msg) {		
		ModelMap result = new ModelMap();
		result.put(FishConstant.SUCCESS, true);
		result.put("ret", "RET_00");
		
		IRetaincard retaincardInfo = retainService.make();
		retaincardInfo.setTerminalId(msg.getTermId());
		retaincardInfo.setAccountNo(msg.getAccountNo());
		retaincardInfo.setReason(msg.getReason());
		retaincardInfo.setStatus(CardStatus.WAIT_RECEIVE);
		retaincardInfo.setCardRetainTime(new Date());
		retaincardInfo.setCardRetainType(CardRetainType.AUTOMATIC_CARD);
		try{
			collectService.collectATMCRetainCard(msg.getTermId(), retaincardInfo);
		}catch(Exception e){
//			logger.error(String.format("处理ATMC吞卡信息异常![%s],吞卡内容:[%s]",e,JsonUtils.toJson(msg)));
			logger.error(String.format("collection retain card info error![%s],retain card info is:[%s]",e,JsonUtils.toJson(msg)));
		}		
		return result;
	}
}
