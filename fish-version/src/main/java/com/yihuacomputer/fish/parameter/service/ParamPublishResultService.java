package com.yihuacomputer.fish.parameter.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamPublishMsg;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;

@Service
@Transactional
public class ParamPublishResultService implements IParamPublishResultService {

	@Autowired
	private IGenericDao dao;
	@Autowired
	private IParamPublishService paramPublishService;

	@Override
	public IParamPublishResult make() {
		return new ParamPublishResult();
	}

	@Override
	public IParamPublishResult save(IParamPublishResult publishResult) {
		return dao.save(publishResult);
	}

	@Override
	public IParamPublishResult get(long id) {
		return dao.get(id, IParamPublishResult.class);
	}

	@Override
	public IParamPublishResult update(IParamPublishResult publishResult) {
		return dao.update(publishResult);
	}

	@Override
	public IParamPublishResult update(long id, String ret) {
		IParamPublishResult paramPublishResult = this.get(id);
		paramPublishResult.setRet(ret);
		return dao.update(paramPublishResult);
	}

	private static final String PARAM_PUSH_URL = "/ctr/paramUpdateNotify";

	public boolean notice(ParamPublishMsg msg, IDevice device) {
		String url = getNoticetUrl(device.getIp());
		IParamPublishResult result = get(msg.getTaskId());
		if (result != null) {
			result.setDownloadStartTime(DateUtils.getTimestamp(new Date()));
			ParamPublishMsg responseMsg = (ParamPublishMsg) HttpProxy.httpPost(url, msg, ParamPublishMsg.class, 30000);
			// 将下发结果回填至业务功能下发结果表
			if (responseMsg != null) {
				result.setDownloadFinishTime(DateUtils.getTimestamp(new Date()));
				result.setRet(StringUtils.isEmpty(responseMsg.getRet()) ? "02" : responseMsg.getRet());
				update(result);
				return true;
			}
		}
		return false;

	}

	private String getNoticetUrl(ITypeIP ip) {
		return MonitorCfg.getHttpUrl(ip.toString()) + PARAM_PUSH_URL;
	}

	@Override
	public IParamPublishService getParamPublishService() {
		// TODO Auto-generated method stub
		return paramPublishService;
	}

}
