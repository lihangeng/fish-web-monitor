package com.yihuacomputer.fish.web.atm;

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

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTransService;
import com.yihuacomputer.fish.web.atm.format.TransactionMsg;

/**
 * 设备交易报文处理的服务接口
 *
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/transaction")
public class TransactionController{

	private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private ICollectService collectService;

    @Autowired
    private ITransactionService transService;
    
    @Autowired
    private IUncommonTransService service;

    /**
     * 接收设备定时状态报告
     *
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap acceptStatus(@RequestBody TransactionMsg msg)
    {
        logger.info(String.format("collection transaction :[%s]",JsonUtils.toJson(msg)));

        ModelMap result = new ModelMap();
        result.addAttribute("Ret", "00");
        try{
	        ITransaction trans = transService.make();
	        trans.setTerminalId(msg.getTermId());
	        trans.setAmt(msg.getAmt());
	        trans.setCreditAccount(msg.getCreditAccount());
	        trans.setCurrency(msg.getCurrency());
	        trans.setDateTime(new Date(msg.getDateTime()));
	        trans.setTransDate(msg.getTransDate());
	        trans.setDebitAccount(msg.getDebitAccount());
	        trans.setHostRet(msg.getHostRet());
	        trans.setLocalRet(msg.getLocalRet());
	        trans.setTransCode(msg.getTransCode());
	        trans.setTransId(msg.getTransId());
	        trans.setTipFee(msg.getTipFee());

        	collectService.collectATMCTransaction(msg.getTermId(), trans);

        }catch(Exception e){
        	e.printStackTrace();
            logger.error(String.format("collection transaction exception![%s],transaction is [%s]",e,JsonUtils.toJson(msg)));
        }

        return result;
    }

}
