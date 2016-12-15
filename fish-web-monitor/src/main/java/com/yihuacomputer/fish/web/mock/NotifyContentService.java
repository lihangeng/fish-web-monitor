package com.yihuacomputer.fish.web.mock;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.fault.INotifyContentService;
import com.yihuacomputer.fish.api.fault.INotifyMouldSet;

/**
 * 服务短信内容组织
 * @author fish
 *
 */
@Service
@Transactional(readOnly = true)
public class NotifyContentService implements INotifyContentService{

	private Logger logger = LoggerFactory.getLogger(NotifyContentService.class);

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	@Override
	public String handleNotifyContent(String notifyContent,
			INotifyMouldSet notifyMouldSet) {

		String terminalId = notifyMouldSet.getTerminalId()==null?"":notifyMouldSet.getTerminalId();
		String notifyContentValue = StringUtils.replaceLogRule(notifyContent, "\\{terminalId\\}", terminalId);
		notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{hwCode\\}", notifyMouldSet.getAppStatus()==null?"":notifyMouldSet.getHwCode());
		notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{faultMod\\}", notifyMouldSet.getFaultMod()==null?"":getEnumI18n(notifyMouldSet.getFaultMod().getText()));
		notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{faultTime\\}", DateUtils.getTimestamp(new Date()));
		notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{faultClassify\\}", notifyMouldSet.getFaultClassify());
		notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{appStatus\\}", notifyMouldSet.getAppStatus()==null?"":getEnumI18n(notifyMouldSet.getAppStatus().getText()));
		if(!"".equals(terminalId)){
			IDevice device = deviceService.get(terminalId);
			notifyContentValue = StringUtils.replaceLogRule(notifyContentValue, "\\{orgName\\}", device.getOrganization().getName());
		}

//		logger.info("短信内容:"+notifyContent);
		logger.info("SMS content:"+notifyContentValue);
		return notifyContentValue;
	}
}
