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
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{terminalId\\}", terminalId);
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{hwCode\\}", notifyMouldSet.getAppStatus()==null?"":notifyMouldSet.getHwCode());
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{faultMod\\}", notifyMouldSet.getFaultMod()==null?"":getEnumI18n(notifyMouldSet.getFaultMod().getText()));
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{faultTime\\}", DateUtils.getTimestamp(new Date()));
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{faultClassify\\}", notifyMouldSet.getFaultClassify());
		notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{appStatus\\}", notifyMouldSet.getAppStatus()==null?"":getEnumI18n(notifyMouldSet.getAppStatus().getText()));
		if(!terminalId.equals("")){
			IDevice device = deviceService.get(terminalId);
			notifyContent = StringUtils.replaceLogRule(notifyContent, "\\{orgName\\}", device.getOrganization().getName());
		}

//		logger.info("短信内容:"+notifyContent);
		logger.info("SMS content:"+notifyContent);
		return notifyContent;
	}
}
