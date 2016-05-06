package com.yihuacomputer.fish.parameter.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamDownloadResultForm;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;
import com.yihuacomputer.fish.version.notice.NoticeForm;

@Service
@Transactional
public class ParamPublishResultService implements IParamPublishResultService {

	@Autowired
	private IGenericDao dao;
	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IParamPublishAppResultService paramPublishAppResultService;
	@Autowired
	private IAppSystemService appSystemService;

	@Autowired
	private MessageSource messageSourceEnum;

	private Logger logger = LoggerFactory.getLogger(ParamPublishResultService.class);

	@Autowired
	private MessageSource messageSourceVersion;

	@Override
	public IParamPublishResult make() {
		return new ParamPublishResult();
	}

	@Override
	public IParamPublishResult save(IParamPublishResult publishResult) {
		return  dao.save(publishResult);
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

	public boolean notice(IParamPublishResult publishResult, IDevice device) {
		int retResult = 0;
		try {
			String url = getNoticetUrl(device.getIp());
			NoticeForm msg = new NoticeForm();

			msg.setTaskId(publishResult.getId());
			msg.setPatchNo(String.valueOf(publishResult.getVersionNo()));
			if (publishResult != null) {
				msg.setPatch(ParamInfo.class.getSimpleName());
				msg.setServerPath(VersionCfg.getAtmParamDir() + File.separator + msg.getPatchNo());
				publishResult.setDownloadStartTime(DateUtils.getTimestamp(new Date()));
				msg = (NoticeForm) HttpProxy.httpPost(url, msg, NoticeForm.class, 30000);

				if (msg.getRet().equals("RET0100")) {
					retResult = 1;
				} else {
					retResult = 2;
					publishResult.setReason("");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			retResult = -1;
			return false;
		} finally {
			if (retResult == 1) {
				publishResult.setRet(TaskStatus.NOTICED_FAIL);
				publishResult.setReason(messageSourceVersion.getMessage("exception.task.sameTaskRuningForAgentRefuse", null, FishCfg.locale));
				publishResult.setSuccess(false);
			} else if (retResult == 2) {
				publishResult.setRet(TaskStatus.NOTICED);
				publishResult.setSuccess(true);
			} else if (retResult == -1) {
				publishResult.setRet(TaskStatus.NOTICED_FAIL);
				publishResult.setSuccess(false);
			}
			List<IAppSystem> systemList = appSystemService.list();
			publishResult = dao.update(publishResult);
			for (IAppSystem appSystem : systemList) {
				IParamPublishAppResult appResult = paramPublishAppResultService.make();
				appResult.setAppSystem(appSystem);
				appResult.setParamPublishResult(publishResult);
				appResult.setStatus(publishResult.getRet());
				appResult.setReason(publishResult.getReason());
				paramPublishAppResultService.save(appResult);
			}
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

	@Override
	public IPageResult<ParamDownloadResultForm> page(int start, int limit, IFilter filter) {
		return dao.page(start, limit, filter, ParamPublishResult.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<ParamDownloadResultForm> getByPublishId(int start, int limit, IFilter filter, String publishId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select ppr,device from ParamPublishResult ppr ");
		hql.append(",Device device where ppr.deviceId=device.id and ppr.ret != 'NEW' ");
		if (publishId != null && !publishId.isEmpty()) {
			hql.append("and ppr.paramPublish.id= ").append(Long.valueOf(publishId));
		}
		IPageResult<Object> list = (IPageResult<Object>) dao.page(start, limit, filter, hql.toString());
		List<ParamDownloadResultForm> result = new ArrayList<ParamDownloadResultForm>();
		for (Object object : list.list()) {
			ParamDownloadResultForm form = new ParamDownloadResultForm();
			Object[] objects = (Object[]) object;
			IParamPublishResult ppr = (ParamPublishResult) objects[0];
			IDevice device = (Device) objects[1];
			form.setId(ppr.getId());
			form.setDeviceId(ppr.getDeviceId());
			form.setTerminalId(device.getTerminalId());
			form.setDownloadStartTime(ppr.getDownloadStartTime());
			form.setDownloadFinishTime(ppr.getDownloadFinishTime());
			form.setReason(ppr.getReason());
			form.setSuccess(ppr.isSuccess());
			form.setVersionNo(ppr.getVersionNo());
			if (ppr.getRet() != null) {
				form.setTaskStatus(getEnumI18n(ppr.getRet().getText()));
			}
			result.add(form);
		}
		return new PageResult<ParamDownloadResultForm>(list.getTotal(), result);
	}

	private String getEnumI18n(String enumText) {
		if (null == enumText) {
			return "";
		}
		return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
	}
	@Override
	public List<IParamPublishAppResult> getStatus(long pubId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select ppar from ParamPublishAppResult ppar where ppar.paramPublishResult.id =?");
		return dao.findByHQL(hql.toString(), pubId);
	}
}
