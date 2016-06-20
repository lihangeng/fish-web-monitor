package com.yihuacomputer.fish.web.atm;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.parameter.service.ParamPublishService;
import com.yihuacomputer.fish.web.atm.format.AutoUpdateMsg;
import com.yihuacomputer.fish.web.atm.format.PatchMsg;
import com.yihuacomputer.fish.web.atm.format.SimpleVersion;

/**
 * 参数下发
 * 
 * @author zhengnan
 *
 */

@Controller
@RequestMapping("/msg/paramUpdate")
public class ParamAutoUpdateController {

	private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IParamPublishResultService paramPublishResultService;

	/**
	 * 接收参数自动更新请求
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AutoUpdateMsg reciveMsg(@RequestBody AutoUpdateMsg msg) {
		IDevice device = deviceService.get(msg.getTermId());
		if(null==device){
			logger.error(String.format("device %s not exist", msg.getTermId()));
			return msg;
		}
		try {
			List<SimpleVersion>list = msg.getCurrentPatches();
			//获取服务器上当前最大版本号
			Map<String,Long> deviceMap = paramPublishService.getMaxVersionNoInfoByDeviceId(device.getId());
			long versionNo = deviceMap.get(ParamPublishService.MAX_VERSION_TIMESTAMP);
			//根据客户端反馈判断是否要执行自动更新操作
			boolean isUpdating = true;
			for(SimpleVersion patchMsg :list){
				long patchNo = Long.parseLong(patchMsg.getVersionNo());
				if(patchMsg.getTypeName().equals(ParamInfo.class.getSimpleName())&&versionNo<=patchNo){
					isUpdating = false;
				}
			}
			if(isUpdating){
				IParamPublishResult paramPublishResult = paramPublishResultService.getParamPublishResult(device.getId(),versionNo);
				if(null==paramPublishResult){
					String date = DateUtils.getTimestamp(new Date());
					IParamPublish paramPublish = paramPublishService.make();
					paramPublish.setJobType(JobType.AUTO_UPDATE);
					paramPublish.setDate(date);
					paramPublish.setPublisher(1);
					paramPublishService.save(paramPublish);
					
					logger.debug(String.format("paramPublish job create success,id is %d",paramPublish.getId()));
					paramPublishResult = paramPublishResultService.make();
					paramPublishResult.setDevice(device);
					paramPublishResult.setDeviceId(device.getId());
					paramPublishResult.setParamPublish(paramPublish);
					paramPublishResult.setRet(TaskStatus.NEW);
					paramPublishResult.setDownloadStartTime(date);
					paramPublishResult.setVersionNo(versionNo);
					paramPublishResultService.save(paramPublishResult);
				}
				logger.debug(String.format("paramPublishResult task create success,id is %d",paramPublishResult.getId()));
				
				//将自动升级任务增加到Msg中进行返回操作
				PatchMsg patchMsg = new PatchMsg();
				patchMsg.setTaskId(paramPublishResult.getId());
				patchMsg.setServerPath(VersionCfg.getAtmParamDir()+File.separator+versionNo);
				patchMsg.setPatchNo(String.valueOf(paramPublishResult.getVersionNo()));
				patchMsg.setPatch(ParamInfo.class.getSimpleName());
				List<PatchMsg> patchMsgList = new ArrayList<PatchMsg>();
				patchMsgList.add(patchMsg);
				msg.setAutoUpdatePatches(patchMsgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
   
}
