package com.yihuacomputer.fish.web.atm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.business.IBoxSettleDetail;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.web.atm.format.SettlementMsg;

/**
 * 提供ATM设备清机服务接口
 *
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/checkoutcash")
public class SettlementController {

	private Logger logger = LoggerFactory.getLogger(SettlementController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired(required=false)
	private IDeviceBoxDetailInfoService deviceBoxDetailInfoService;
	@Autowired(required=false)
	private IDeviceService deviceService;
	@Autowired(required=false)
	private IDeviceBoxInfoService deviceBoxInfoService;
	
	@Autowired
	private ISettlementService settlementService;
	/**
	 * 接收设备清机结账信息
	 * @param runInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody SettlementMsg msg) {

		ModelMap result = new ModelMap();
		result.put("ret", "RET_00");

		ISettlement settlementInfo=settlementService.make();
		settlementInfo.setTerminalId(msg.getTermId());
		settlementInfo.setDate(msg.getDate());
		settlementInfo.setUuId(msg.getUuId());
		settlementInfo.setLeftAmt(msg.getLeftAmt());
		settlementInfo.setDeposit(msg.getDeposit());
		settlementInfo.setDepositAmt(msg.getDepositAmt());
		settlementInfo.setWithdrawal(msg.getWithdrawal());
		settlementInfo.setWithdrawalAmt(msg.getWithdrawalAmt());
		settlementInfo.setTransactionAmt(msg.getTransactionAmt());
		settlementInfo.setTransaction(msg.getTransaction());
		List<IBoxSettleDetail> boxDetail =new ArrayList<IBoxSettleDetail> ();
		boxDetail.addAll(msg.getBoxDetail());
		settlementInfo.setBoxDetail(boxDetail);
		
		try{
			collectService.collectSettlement(msg.getTermId(), settlementInfo);
			if(deviceBoxDetailInfoService!=null){
				IDevice device = deviceService.get(msg.getTermId());
				if(device!=null){
					IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.findByDeviceId(device.getId());
					if(deviceBoxInfo!=null){
						boolean updateResult = deviceBoxDetailInfoService.updateBoxDetailEffect(deviceBoxInfo.getId());
						logger.debug(String.format("update %s boxDetail %s",msg.getTermId(),updateResult));
					}
				}
			}
		}catch(Exception e){
			logger.error(String.format("collection settlementInfo exception![%s],settlementInfo is [%s]",e,JsonUtils.toJson(msg)));
		}

		return result;
	}
}
