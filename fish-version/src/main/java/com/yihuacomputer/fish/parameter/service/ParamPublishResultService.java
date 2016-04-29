package com.yihuacomputer.fish.parameter.service;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;
import com.yihuacomputer.fish.version.notice.NoticeForm;

@Service
@Transactional
public class ParamPublishResultService implements IParamPublishResultService {

	@Autowired
	private IGenericDao dao;
	@Autowired
	private IParamPublishService paramPublishService;

	private Logger logger = LoggerFactory.getLogger(ParamPublishResultService.class);

	@Autowired
	private MessageSource messageSourceVersion;

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
		return dao.get(id, ParamPublishResult.class);
	}

	@Override
	public IParamPublishResult update(IParamPublishResult publishResult) {
		return dao.update(publishResult);
	}

	@Override
	public IParamPublishResult update(long id, String ret) {
		IParamPublishResult paramPublishResult = this.get(id);
		paramPublishResult.setRet(TaskStatus.valueOf(ret));
		return dao.update(paramPublishResult);
	}

	private static final String PARAM_PUSH_URL = "/ctr/paramUpdateNotify";

	public boolean notice(IParamPublishResult result, IDevice device) {
		String url = getNoticetUrl(device.getIp());
		NoticeForm msg = new NoticeForm();

		int retResult = 0;
		msg.setTaskId(result.getId());
		msg.setPatchNo(String.valueOf(result.getVersionNo()));
		if (result != null) {
			msg.setPatch(ParamInfo.class.getSimpleName());
			msg.setServerPath(VersionCfg.getAtmParamDir() + File.separator + msg.getPatchNo());
			result.setDownloadStartTime(DateUtils.getTimestamp(new Date()));
			try {
				msg = (NoticeForm) HttpProxy.httpPost(url, msg, NoticeForm.class, 30000);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (msg.getRet().equals("RET0100")) {
				retResult = 1;
				result.setReason(messageSourceVersion.getMessage("exception.task.sameTaskRuningForAgentRefuse", null, FishCfg.locale));
			} else {
				retResult = 2;
				result.setReason("");
			}
		}
		if (retResult == 1) {
			result.setRet(TaskStatus.NOTICED_FAIL);
			result.setSuccess(false);
		} else if (retResult == 2) {
			result.setRet(TaskStatus.NOTICED);
			result.setSuccess(true);
		} else if (retResult == -1) {
			result.setRet(TaskStatus.NOTICED_FAIL);
			result.setSuccess(false);
		}
		return true;

	}

	private String getNoticetUrl(ITypeIP ip) {
		return MonitorCfg.getHttpUrl(ip.toString()) + PARAM_PUSH_URL;
	}

	@Override
	public IParamPublishService getParamPublishService() {
		return paramPublishService;
	}

	@Override
	public IParamPublishResult getParamPublishResult(long deviceId, long versionNo) {
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(ParamPublishResult.class.getSimpleName()).append(" result ").append("where result.deviceId=? and result.versionNo=? and result.paramPublish.jobType=?");

		return dao.findUniqueByHql(sb.toString(), new Object[] { deviceId, versionNo, JobType.AUTO_UPDATE });
	}

}
