package com.yihuacomputer.fish.fault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultFlowService;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.fault.IFaultClassify;

/**
 * 默认故障流程处理,服务器直接创建短信或邮件
 * @author GQ
 *
 */
@Service
public class DefaultCaseFaultFlowService implements ICaseFaultFlowService {

	@Autowired
	private ICaseNotifyService caseNotifyService;
	@Override
	public void execute(IFaultClassify faultClassify,ICaseFault caseFault) {
		caseNotifyService.createCaseNotify(faultClassify, caseFault);
	}

}
