package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoneyService;
import com.yihuacomputer.fish.web.atm.format.CounterFeitMoneyMsg;
import com.yihuacomputer.fish.api.monitor.business.INoteItem;

/**
 * 设备交易报文处理的服务接口
 *
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/counterFeitMoney")
public class CounterFeitMoneyController{

	private Logger logger = LoggerFactory.getLogger(CounterFeitMoneyController.class);

    @Autowired
    private ICollectService collectService;

    @Autowired
    private ICounterFeitMoneyService service;

    /**
     * 接收设备定时状态报告
     *
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap acceptStatus(@RequestBody CounterFeitMoneyMsg msg)
    {
        logger.info(String.format("tranaction info is :[%s]",JsonUtils.toJson(msg)));

        ModelMap result = new ModelMap();
        result.addAttribute("Ret", "00");
        try{
        	ICounterFeitMoney counterFeitMoney = service.make();
        	counterFeitMoney.setTerminalId(msg.getTermId());
        	counterFeitMoney.setAmt(msg.getAmt());
        	counterFeitMoney.setCreditAccount(msg.getCreditAccount());
        	counterFeitMoney.setCurrency(msg.getCurrency());
        	counterFeitMoney.setDateTime(new Date(msg.getDateTime()));
        	counterFeitMoney.setTransDate(msg.getTransDate());
        	counterFeitMoney.setDebitAccount(msg.getDebitAccount());
        	counterFeitMoney.setHostRet(msg.getHostRet());
        	counterFeitMoney.setLocalRet(msg.getLocalRet());
        	counterFeitMoney.setTransCode(msg.getTransCode());
        	counterFeitMoney.setTransId(msg.getTransId());
        	counterFeitMoney.setTipFee(msg.getTipFee());
        	counterFeitMoney.setCounterFeitMoney(msg.getCounterFeitMoney());
        	List<INoteItem> noteResultList =new ArrayList<INoteItem>();
        	noteResultList.addAll(msg.getNoteItem());
        	counterFeitMoney.setNoteItem(noteResultList);
        	collectService.collectATMCCounterFeitMoney(msg.getTermId(), counterFeitMoney);
        }catch(Exception e){
            logger.error(String.format("collection transaction info fail![%s],transaction context is:[%s]",e,JsonUtils.toJson(msg)));
        }
        
        return result;
    }

}
