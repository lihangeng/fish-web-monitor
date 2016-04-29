package com.yihuacomputer.fish.web.atm;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author GQ
 * 接收参数更新进度
 */
@Controller
@RequestMapping("/msg/paramUpdateResult")
public class ParamUpdateResultController {
	

	private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IParamPublishResultService paramPublishResultService;
	@Autowired
	private IParamPublishAppResultService paramPublishAppResultService;
	@Autowired
	private IAppSystemService appSystemService;

	@Autowired
	private MessageSource messageSourceVersion;
	/**
	 * 接收参数上报更新状态
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ParamInfo reciveMsg(@RequestBody ParamInfo msg) {
		//获取上报的参数归属应用
		String appType = msg.getAppType();
		//将结果RET进行转换
		TaskStatus status = null;
		String reason = null;
		AgentRet agentRet = AgentRet.valueOf(msg.getRet());
        if (agentRet.equals(AgentRet.RET40)) {
        	status = TaskStatus.DOWNLOADED;
        } else if (AgentRet.isDownFail(agentRet)) {
        	status = TaskStatus.DOWNLOADED_FAIL;
        	reason = getEnumI18n(agentRet.getText());
        } else if (agentRet.equals(AgentRet.RET50)) {
            status = TaskStatus.DEPLOYED;
        } else if (agentRet.equals(AgentRet.RET51)) {
            status = TaskStatus.DEPLOYED_WAIT;
        } else if (AgentRet.isDeployFail(agentRet)) {
            status = TaskStatus.DEPLOYED_FAIL;
            reason = getEnumI18n(agentRet.getText());
        } else {
            status = TaskStatus.OTHER;
        }
        //上报全局状态
        if(ParamInfo.class.getSimpleName().equals(appType)){
			IParamPublishResult paramPublishResult = paramPublishResultService.get(msg.getTaskId());
			if(null==paramPublishResult){
				logger.error(String.format("param update task %d not exist", msg.getTaskId()));
				return null;
			}
			paramPublishResult.setReason(reason);
			paramPublishResult.setRet(status);
			try {
				paramPublishResult = paramPublishResultService.update(paramPublishResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        //上报指定的应用参数
        else{
        	IParamPublishAppResult paramPublishAppResult = paramPublishAppResultService.get(msg.getTaskId(), appType);
        	if(null==paramPublishAppResult){
        		IAppSystem appSystem = appSystemService.get(appType);
        		if(appSystem==null){
        			logger.error(String.format("appType %s not exist",appType));
        			return msg;
        		}
        		IParamPublishResult paramPublishResult = paramPublishResultService.get(msg.getTaskId());
        		if(null==paramPublishResult){
        			logger.error(String.format("paramPublishResult [Task] %d not exist",msg.getTaskId()));
        			return msg;
        		}
        		paramPublishAppResult = paramPublishAppResultService.make();
        		paramPublishAppResult.setAppSystem(appSystemService.get(appType));
        		paramPublishAppResult.setParamPublishResult(paramPublishResult);
        	}
    		paramPublishAppResult.setStatus(status);
    		paramPublishAppResult.setReason(reason);
    		paramPublishAppResultService.save(paramPublishAppResult);
    		List<IParamPublishAppResult> appResultList = paramPublishAppResultService.list(msg.getTaskId());
    		List<IAppSystem> appSystemList = appSystemService.list();
    		//如果结果全部上报了，则修改全局状态
    		if(appResultList.size() == appSystemList.size()){
    			IParamPublishResult paramPublishResult = paramPublishResultService.get(msg.getTaskId());
    			TaskStatus paramStatus = paramPublishResult.getRet();
    			for(IParamPublishAppResult appResult:appResultList){
    				if(appResult.getStatus().getId()>paramStatus.getId()){
    					paramPublishResult.setRet(appResult.getStatus());
    					paramPublishResult.setReason(appResult.getReason());
    				}
    			}
				paramPublishResult.setDownloadFinishTime(DateUtils.getTimestamp(new Date()));
    			paramPublishResultService.update(paramPublishResult);
    		}
        }
		return msg;
	}

	private String getEnumI18n(String key){
		return messageSourceVersion.getMessage(key,null, FishCfg.locale);
	}

}
enum AgentRet {
    /**
     * 成功
     */
    RET00("AgentRet.RET00"), // 成功
    /**
     * 失败
     */
    RET01("AgentRet.RET01"), // 失败
    /**
     * 相同的软件分类正在升级
     */
    RET0100("AgentRet.RET0100"),
    /**
     * ATC应用忙
     */
    RET02("AgentRet.RET02"), // ATMC应用忙
    /**
     * Agent异常
     */
    RET03("AgentRet.RET03"), // Agent异常
    /**
     * 升级包下载成功
     */
    RET40("AgentRet.RET40"),
    /**
     * 升级包下载失败
     */
    RET41("AgentRet.RET41"),
    /**
     * D盘磁盘空间不足
     */
    RET4100("AgentRet.RET4100"),
    /**
     * 部署路径所在盘磁盘空间不足
     */
    RET4101("AgentRet.RET4101"),
    /**
     * 部署路径所在盘不存在
     */
    RET4102("AgentRet.RET4102"),
    /**
     * 部署路径没有配置
     */
    RET4103("AgentRet.RET4103"),
    /**
     * 服务器中断
     */
    RET4104("AgentRet.RET4104"),
    /**
     * 下发文件MD5值校验失败
     */
    RET4105("AgentRet.RET4105"),
    /**
     * 分发的版本号比ATM上已有的版本号低,无需分发
     */
    RET4106("AgentRet.RET4106"),
    /**
     * 正在部署
     */
    RET50("AgentRet.RET50"),
    /**
     * 等待部署
     */
    RET51("AgentRet.RET51"),
    /**
     * 部署失败
     */
    RET52("AgentRet.RET52"),
    /**
     * 其它
     */
    RET99("AgentRet.RET99");

    private String text;

    private AgentRet(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static boolean isDownFail(AgentRet agentRet) {
        if (agentRet.name().startsWith("RET41")) {
            return true;
        }
        return false;
    }

    public static boolean isDeployFail(AgentRet agentRet) {
        if (agentRet.name().startsWith("RET52")) {
            return true;
        }
        return false;
    }

}
