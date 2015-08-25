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
import com.yihuacomputer.fish.api.monitor.ICollectService;
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
		}catch(Exception e){
			logger.error(String.format("处理ATMC结账异常![%s],请求信息[%s]",e,JsonUtils.toJson(msg)));
		}

		return result;
	}
}
