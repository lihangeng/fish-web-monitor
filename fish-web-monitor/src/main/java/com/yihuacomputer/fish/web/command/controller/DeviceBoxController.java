package com.yihuacomputer.fish.web.command.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.CashBoxDetail;
import com.yihuacomputer.fish.web.command.format.DeviceBoxMsg;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("agent/boxdetail")
public class DeviceBoxController {

	@Autowired
	private MessageSource messageSourceEnum;
	
	private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	/**
	 * @param id terminalId终端编号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deviceBox", method = RequestMethod.GET)
	public @ResponseBody ModelMap getDeviceBox(@RequestParam String id,WebRequest request) {
        ModelMap result = new ModelMap();

		String url = MonitorCfg.getHttpUrl(request.getParameter("id"))+"/ctr/boxdetail";

        DeviceBoxMsg msg = (DeviceBoxMsg)HttpProxy.httpGet(url,DeviceBoxMsg.class, 5000);
        
        if(msg != null){
        	List<CashBoxDetail> list = msg.getBoxList();
            for(CashBoxDetail cashBoxDetail:list){
            	cashBoxDetail.setBinStatusName(getEnumI18n(cashBoxDetail.getBinStatus()));
            	cashBoxDetail.setTypeName(getEnumI18n(cashBoxDetail.getType()));
            }
        	result.addAttribute(FishConstant.SUCCESS, true);
        	result.addAttribute("data", msg);
        }else{
        	result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
	}
}
