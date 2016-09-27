package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUnique;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUniqueService;
import com.yihuacomputer.fish.api.monitor.business.IBoxInitDetail;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.web.atm.format.CashInitalMsg;

/**
 * 提供ATM加钞处理服务接口
 *
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/checkincash")
public class CashInitalController {

	private Logger logger = LoggerFactory.getLogger(CashInitalController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private ICashInitService cashInitService;

	@Autowired
	private ICashInitUniqueService cashInitUniqueService;
	
	@Autowired
	protected MessageSource messageSource;
	/**
	 * 采集设备应用加钞信息
	 * @param runInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody CashInitalMsg msg) {
		ModelMap result = new ModelMap();
		result.put(FishConstant.SUCCESS, true);
        result.addAttribute("ret", "RET_00");
		result.put("data", msg);

		ICashInit cashInitInfo = cashInitService.make();
		cashInitInfo.setUuId(msg.getUuId());
		cashInitInfo.setTerminalId(msg.getTermId());
		cashInitInfo.setAmt(msg.getAmt());
		cashInitInfo.setDate(msg.getDate());
		List<IBoxInitDetail> boxDetailList = new ArrayList<IBoxInitDetail>();
		boxDetailList.addAll(msg.getBoxDetail());
		cashInitInfo.setBoxDetail(boxDetailList);
		ICashInitUnique cashInitUnique = cashInitUniqueService.getByTerminalId(msg.getTermId());
		boolean isNewDevice = false;
		if(null==cashInitUnique){
			isNewDevice = true;
			cashInitUnique = cashInitUniqueService.make();
			cashInitUnique.setTerminalId(msg.getTermId());
		}
		cashInitUnique.setAmt(msg.getAmt());
		cashInitUnique.setDate(msg.getDate());
		cashInitUnique.setUuId(msg.getUuId());
		try{
			collectService.collectCashInit(msg.getTermId(), cashInitInfo);
			cashInitUnique = isNewDevice?cashInitUniqueService.save(cashInitUnique):cashInitUniqueService.update(cashInitUnique);
		}catch(Exception e){
			logger.error(String.format(messageSource.getMessage("cashInital.processError", null, FishCfg.locale),e,JsonUtils.toJson(msg)));
		}

		return result;
	}
}
